package com.odx.test.service.task;

import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;

public interface IPalindromeTaskBase<T extends Palindrome> {
	
	public T execute(T palindrome) throws OdxException;

}
