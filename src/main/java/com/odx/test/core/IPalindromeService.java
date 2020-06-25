package com.odx.test.core;

import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;

public interface IPalindromeService {

	public Palindrome savePalindrome(final Palindrome palindrome) throws OdxException;
	
	public Palindrome getPalindrome() throws OdxException;
}
