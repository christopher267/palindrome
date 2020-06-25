package com.odx.test.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.odx.test.core.IPalindromeService;
import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;
import com.odx.test.persist.PalindromeRepository;
import com.odx.test.service.task.IPalindromeTaskBase;
import com.odx.test.service.task.PalindromeTaskFactory;
import com.odx.test.service.task.PalindromeTaskType;

@Component
public class PalindromeService implements IPalindromeService {
	
	private static final Logger logger = LoggerFactory.getLogger(PalindromeService.class);
	
	@Resource
	private PalindromeRepository repo;
	
	@Resource
	private PalindromeTaskFactory<Palindrome> factory;
	
	@Override
	@Transactional(rollbackOn = OdxException.class)
	public Palindrome savePalindrome(final Palindrome palindrome) throws OdxException {
		
		Palindrome result = palindrome;
		for(IPalindromeTaskBase<Palindrome> task : factory.getTasks(PalindromeTaskType.SAVE)) {
			try {
				result = task.execute(result);
			} catch(OdxException ex) {
				final String name = (palindrome != null) ? palindrome.getName() : "Empty Palindome"; 
				logger.error(String.format("Failed to execute task %s for palindrome name of: %s. Error: %s", 
						task.getClass(), name, ex.getMessage()));
				throw ex;
			}
		}
		
		return result;
	}

	@Override
	public Palindrome getPalindrome() throws OdxException {
		List<Palindrome> results = repo.findAll();
		
		if(!CollectionUtils.isEmpty(results) && results.size() > 0) {
			return results.get(0);
		}
		
		return null;
	}
	
}
