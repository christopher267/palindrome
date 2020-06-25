package com.odx.test.web.api;

import java.io.Serializable;

public class PalindromeDto implements Serializable {

	public PalindromeDto(String palindrome) {
		super();
		this.name = palindrome;
	}
	
	public PalindromeDto() {
		
	}

	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
