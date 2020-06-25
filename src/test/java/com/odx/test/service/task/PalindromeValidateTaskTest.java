package com.odx.test.service.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.odx.test.MockTestBase;
import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;

public class PalindromeValidateTaskTest extends MockTestBase {

	@InjectMocks
	private PalindromeValidateTask task;
	
	@Test
	public void testExecute_Valid() throws Exception {
		Palindrome palindrome = PalindromeTestHelper.getPalindrome("wow");
		try{
			task.execute(palindrome);
		} catch (OdxException ex) {
			fail("Palindrome with a name should not throw a validation exception");
		}
	}
	
	@Test
	public void testExecute_EmptyRequest() throws Exception {
		Palindrome palindrome = PalindromeTestHelper.getPalindrome("");
		try{
			task.execute(palindrome);
		} catch (OdxException ex) {
			assertEquals(PalindromeValidateTask.EMPTY_OR_NULL_VALIDATION_MESSAGE, ex.getMessage());
		}
	}	
	
	@Test
	public void testExecute_NullRequest() throws Exception {
		Palindrome palindrome = null;
		try{
			task.execute(palindrome);
		} catch (OdxException ex) {
			assertEquals(PalindromeValidateTask.EMPTY_OR_NULL_VALIDATION_MESSAGE, ex.getMessage());
		}
	}
	
	@Test
	public void testExecute_InvalidInput() throws Exception {
		Palindrome palindrome = PalindromeTestHelper.getPalindrome(PalindromeTestHelper.getRandomName(10001));
		try{
			task.execute(palindrome);
		} catch (OdxException ex) {
			assertEquals(PalindromeValidateTask.PALINDROME_NAME_TOO_LONG_VALIDATION_MESSAGE, ex.getMessage());
		}
	}

}
