package com.odx.test.service.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.odx.test.MockTestBase;
import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;

public class PalindromeFindTaskTest extends MockTestBase {

	@InjectMocks
	private PalindromeFindTask task;
	
	@Test
	public void testExecute_Success() throws Exception {
		final String EXPECTED_PALINDROME = "kayAk";
		final String input = "Random sentence kayAk palindrome";
		Palindrome palindrome = PalindromeTestHelper.getPalindrome(input);
		palindrome = task.execute(palindrome);
		assertNotNull(palindrome);
		assertNotNull(palindrome.getName());
		assertEquals(EXPECTED_PALINDROME, palindrome.getName());
	}
	
	@Test
	public void testSavePalindrome_EmptyName() throws Exception {
		final Palindrome palindrome = PalindromeTestHelper.getPalindrome("");
		try {
			task.execute(palindrome);
		} catch(OdxException ex) {
			fail("Empty name should not cause a failure.");
		}
	}

}
