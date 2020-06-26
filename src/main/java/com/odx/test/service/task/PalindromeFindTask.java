package com.odx.test.service.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.odx.test.core.exception.ExceptionType;
import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;
import com.odx.test.service.utils.PalindromeUtils;

@Component
public class PalindromeFindTask implements IPalindromeTaskBase<Palindrome> {

	public static final String TOKEN_DELIMETER = " ";
	public static final String NO_PALINDROME_FOUND_ERROR_MESSAGE = "No palindrome found";
	
	@Override
	public Palindrome execute(final Palindrome palindrome) throws OdxException {
		
		String name = palindrome.getName();
		List<String> palindromes = new ArrayList<>();
		for(int leftIndex = 0; leftIndex < name.length(); leftIndex++) {
			for(int rightIndex = name.length()-1; rightIndex > -1; rightIndex--) {
				if(leftIndex > rightIndex) {
					break;
				}
				String leftSide = PalindromeUtils.cleanPalindrome(name.substring(leftIndex, rightIndex+1));
				String rightSide = PalindromeUtils.cleanPalindrome((new StringBuffer(name.substring(leftIndex, rightIndex+1))).reverse().toString());
				if(leftSide.equalsIgnoreCase(rightSide)) {
					palindromes.add(name.substring(leftIndex, rightIndex+1).trim());
				}
			}
		}
		
		if(CollectionUtils.isEmpty(palindromes)) {
			throw new OdxException(NO_PALINDROME_FOUND_ERROR_MESSAGE, ExceptionType.INVALID_DATA);
		}
		
		String longestName = palindromes.stream().max(Comparator.comparingInt(String::length)).get();
		Palindrome longestPal = new Palindrome();
		longestPal.setName(longestName);
		
		return longestPal;
	}
}
