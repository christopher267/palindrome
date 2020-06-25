package com.odx.test.web;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.odx.test.core.IPalindromeService;
import com.odx.test.web.api.PalindromeDto;
import com.odx.test.web.api.PalindromeMapper;

@RestController("PalindromeResource")
@RequestMapping(value = "v1/Palindrome")
public class PalindromeResource {

	@Resource
	private IPalindromeService service;
	
	@Resource
	private PalindromeMapper mapper;
	
	@RequestMapping(
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@PermitAll
	public PalindromeDto getPalindrome() throws Exception {
		return mapper.map(service.getPalindrome());
	}
	
	@RequestMapping(
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
	@PermitAll
	public PalindromeDto savePalindrome(@RequestBody final PalindromeDto palindromeDto) throws Exception {
		return mapper.map(service.savePalindrome(mapper.mapReverse(palindromeDto)));
	}
}
