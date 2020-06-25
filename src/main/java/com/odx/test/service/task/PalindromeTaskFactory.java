package com.odx.test.service.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.odx.test.persist.Palindrome;

@Component
public class PalindromeTaskFactory<T extends Palindrome> {

	@Resource
	private PalindromeFindTask findTask;
	
	@Resource
	private PalindromeSaveTask saveTask;
	
	@Resource
	private PalindromeValidateTask validateTask;
	
	public List<IPalindromeTaskBase<Palindrome>> getTasks(PalindromeTaskType type) {
		List<IPalindromeTaskBase<Palindrome>> tasks = new ArrayList<>();
		
		switch(type) {
			case READ:
				break;
			case SAVE:
				tasks.add(validateTask);
				tasks.add(findTask);
				tasks.add(saveTask);
				break;
			case VALIDATE:
				break;
			default:
				break;
		
		}
		
		return tasks;
	}
	
}
