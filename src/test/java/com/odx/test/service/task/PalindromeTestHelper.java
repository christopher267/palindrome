package com.odx.test.service.task;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.odx.test.persist.Palindrome;

public class PalindromeTestHelper {

	public static Palindrome getPalindrome(String name) {
		Palindrome palindrome = new Palindrome();
		palindrome.setName(name);
		return palindrome;
	}
	
	public static List<Palindrome> getPalindromeDbList(List<String> names) {
		return names.stream().map(name -> {
			Palindrome pal = new Palindrome();
			pal.setName(name);
			pal.setId(1l);
			return pal;
		}).collect(Collectors.toList());
	}

	public static String getRandomName(int length) {
		StringBuilder nameBuilder = new StringBuilder();
		Random r = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
	    for (int i = 0; i < length; i++) {
	        nameBuilder.append(alphabet.charAt(r.nextInt(alphabet.length())));
	    }
	    
	    return nameBuilder.toString();
	}
}
