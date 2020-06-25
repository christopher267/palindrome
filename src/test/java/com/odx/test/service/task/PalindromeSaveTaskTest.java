package com.odx.test.service.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.odx.test.MockTestBase;
import com.odx.test.persist.Palindrome;
import com.odx.test.persist.PalindromeRepository;

public class PalindromeSaveTaskTest extends MockTestBase {

	@Mock
	private PalindromeRepository repo;
	
	@InjectMocks
	private PalindromeSaveTask task;
	
	@Captor
	private ArgumentCaptor<Palindrome> palindromeCaptor;
	
	@Test
	public void testExecute_Success() throws Exception {
		
		final String EXPECTED_NAME = "zeeeez";
		final Palindrome palindrome = PalindromeTestHelper.getPalindrome(EXPECTED_NAME);
		
		Mockito.when((repo.findAll())).thenAnswer(new Answer<List<Palindrome>>() {
		    private int count = 0;

		    public List<Palindrome> answer(InvocationOnMock invocation) {
		        if (++count == 1)
		            return PalindromeTestHelper.getPalindromeDbList(Arrays.asList("wow"));

		        return PalindromeTestHelper.getPalindromeDbList(Arrays.asList(EXPECTED_NAME));
		    }
		});
		
		final Palindrome result = task.execute(palindrome);
		
		assertNotNull(result);
		assertEquals(EXPECTED_NAME, result.getName());
		
		Mockito.verify(repo).saveAndFlush(palindromeCaptor.capture());
		final Palindrome capturedPalindrome = palindromeCaptor.getValue();
		assertNotNull(capturedPalindrome);
		assertNotNull(EXPECTED_NAME, capturedPalindrome.getName());
	}
}
