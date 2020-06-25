package com.odx.test.service.task;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.odx.test.core.exception.ExceptionType;
import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;

@Component
public class PalindromeValidateTask implements IPalindromeTaskBase<Palindrome> {

	public static final String EMPTY_OR_NULL_VALIDATION_MESSAGE = "Palindrome is missing. Please provide valid palindrome name.";
	public static final String PALINDROME_NAME_TOO_LONG_VALIDATION_MESSAGE = "Palindrome name cannot exceed 1000 characters.";
	
	@Override
	public Palindrome execute(final Palindrome palindrome) throws OdxException {
		
		if(palindrome == null || StringUtils.isEmpty(palindrome.getName())) {
			throw new OdxException(EMPTY_OR_NULL_VALIDATION_MESSAGE, ExceptionType.MISSING_DATA);
		} else if(palindrome.getName().length() > 1000) {
			throw new OdxException(PALINDROME_NAME_TOO_LONG_VALIDATION_MESSAGE, ExceptionType.INVALID_DATA);
		}
		
		return palindrome;
	}

}
