package com.odx.test.service.task;

import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;

@Component
public class PalindromeFindTask implements IPalindromeTaskBase<Palindrome> {

	public static final String TOKEN_DELIMETER = " ";
	
	@Override
	public Palindrome execute(final Palindrome palindrome) throws OdxException {
		String name = "";
		
		StringTokenizer tokens = new StringTokenizer(palindrome.getName(), TOKEN_DELIMETER);
		
		while(tokens.hasMoreElements()) {
			final String token = tokens.nextToken();
			final String reversedToken = (new StringBuffer(token)).reverse().toString();
			if(token.equalsIgnoreCase(reversedToken) && token.length() > name.length()) {
				name = token;
			}
		}
		
		Palindrome result = new Palindrome();
		result.setName(name);
		
		return result;
	}

}
