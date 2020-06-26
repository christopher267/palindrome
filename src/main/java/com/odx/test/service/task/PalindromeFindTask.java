package com.odx.test.service.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
		String name = PalindromeUtils.cleanPalindrome(palindrome.getName());
		
		List<String> palindromes = new ArrayList<>();
		for(int leftIndex = 0; leftIndex < name.length(); leftIndex++) {
			int offset = 0;
			StringBuilder palindromeBuilder = new StringBuilder();
			for(int rightIndex = name.length()-1; rightIndex > -1; rightIndex--) {
				if(leftIndex + offset > name.length() - 1) {
					break;
				}
				char leftChar = name.charAt(leftIndex + offset);
				char rightChar = name.charAt(rightIndex);
				if(leftChar == rightChar) {
					palindromeBuilder.append(leftChar);
					offset++;
					if((leftIndex == rightIndex)) {
						palindromes.add(palindromeBuilder.toString());
					}
				} else {
					palindromeBuilder = new StringBuilder();
					rightIndex += offset;
					offset = 0;
					if((leftIndex+1 > rightIndex-1)) {
						break;
					}
				}
			}
		}
		
		if(CollectionUtils.isEmpty(palindromes)) {
			throw new OdxException(NO_PALINDROME_FOUND_ERROR_MESSAGE, ExceptionType.INVALID_DATA);
		}
		
		String longestPalindrome = palindromes.stream().max(Comparator.comparingInt(String::length)).get();
		
		Palindrome result = new Palindrome();
		result.setName(longestPalindrome);
		
		return result;
	}

}
