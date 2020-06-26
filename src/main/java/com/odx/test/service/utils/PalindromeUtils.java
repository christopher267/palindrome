package com.odx.test.service.utils;

public class PalindromeUtils {

	public static String cleanPalindrome(final String name) {
		return name.replaceAll("[^a-zA-Z]", "").toUpperCase();
	}
}
