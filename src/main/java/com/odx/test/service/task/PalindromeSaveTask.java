package com.odx.test.service.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.odx.test.core.exception.ExceptionType;
import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;
import com.odx.test.persist.PalindromeRepository;

@Component
public class PalindromeSaveTask implements IPalindromeTaskBase<Palindrome> {

	public static final String NON_SAVABLE_ERROR_MESSAGE = "Palindrome is not the longest saved and will not be updated.";
	
	@Resource
	private PalindromeRepository repo;
	
	@Override
	public Palindrome execute(final Palindrome palindrome) throws OdxException {
		List<Palindrome> results = repo.findAll();
		final String name = palindrome.getName();
		if(results.size() > 0) {
			final Palindrome existingPalindrome = results.get(0);
			final String existingPalindomeName = existingPalindrome.getName();
			if(StringUtils.isBlank(existingPalindomeName) 
					|| name.length() > existingPalindomeName.length()) {
				existingPalindrome.setName(name);
				repo.saveAndFlush(existingPalindrome);
			} else {
				throw new OdxException(NON_SAVABLE_ERROR_MESSAGE, ExceptionType.INVALID_DATA);
			}
		} else {
			Palindrome newPalindrome = new Palindrome();
			newPalindrome.setName(name);
			repo.saveAndFlush(newPalindrome);
		}
		
		results = repo.findAll();
		
		return results.get(0);
	}

}
