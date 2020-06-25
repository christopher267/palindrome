package com.odx.test.web.api;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.odx.test.core.exception.OdxException;
import com.odx.test.persist.Palindrome;

@Component
public class PalindromeMapper extends ModelMapper {

	public PalindromeDto map(Palindrome palindrome) {
		PalindromeDto palindromeDto = this.map(palindrome, PalindromeDto.class);
	    return palindromeDto;
	}
	
	public Palindrome mapReverse(PalindromeDto palindromeDto) throws OdxException {
	    return this.map(palindromeDto, Palindrome.class);
	}
}
