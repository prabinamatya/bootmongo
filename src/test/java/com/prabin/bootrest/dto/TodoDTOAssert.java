package com.prabin.bootrest.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

public class TodoDTOAssert extends AbstractAssert<TodoDTOAssert, TodoDTO> {

	private TodoDTOAssert(TodoDTO actual) {
		super(actual, TodoDTOAssert.class);
	}
	
	public static TodoDTOAssert assertThatTodoDTO(TodoDTO actual) {
		return new TodoDTOAssert(actual);
	}
	
	public TodoDTOAssert hasDescription(String expectedDescription) {
		isNotNull();
		String actualDescription = actual.getDescription();
		assertThat(actualDescription)
			.overridingErrorMessage("Expected description to be <%s> but was <%s>", 
					expectedDescription, actualDescription)
			.isEqualTo(expectedDescription);
					
		return this;
	}
	
	public TodoDTOAssert hasId(String expectedId) {
		isNotNull();
		String actualId = actual.getId();
		assertThat(actualId)
		.overridingErrorMessage("Expected id to be <%s> but was <%s>", 
				expectedId, actualId)
		.isEqualTo(expectedId);
		return this;
	}
	
	public TodoDTOAssert hasTitle(String expectedTitle) {
		isNotNull();
		String actualTitle = actual.getTitle();
		assertThat(actualTitle)
		.overridingErrorMessage("Expected id to be <%s> but was <%s>", 
				expectedTitle, actualTitle)
		.isEqualTo(expectedTitle);
		return this;
	}
	
	public TodoDTOAssert hasNoId(String expectedId) {
		isNotNull();
		String actualId = actual.getId();
		assertThat(actualId)
		.overridingErrorMessage("Expected id to be null but was <%s>", 
				expectedId, actualId)
		.isNull();
		return this;
	}

	public TodoDTOAssert hasNoDescription(String expectedDescription) {
		isNotNull();
		String actualDescription = actual.getDescription();
		assertThat(actualDescription)
			.overridingErrorMessage("Expected description to be null but was <%s>", 
					expectedDescription, actualDescription)
			.isNull();
					
		return this;
	}
}
