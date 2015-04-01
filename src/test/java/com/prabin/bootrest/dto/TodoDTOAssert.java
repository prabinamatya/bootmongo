package com.prabin.bootrest.dto;

import org.assertj.core.api.AbstractAssert;

public class TodoDTOAssert extends AbstractAssert<TodoDTOAssert, TodoDTO> {

	private TodoDTOAssert(TodoDTO actual) {
		super(actual, TodoDTOAssert.class);
	}
	
	public static TodoDTOAssert assertThatTodoDTOAssert(TodoDTO actual) {
		return new TodoDTOAssert(actual);
	}

}
