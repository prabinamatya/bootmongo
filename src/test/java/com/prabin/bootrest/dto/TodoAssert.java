package com.prabin.bootrest.dto;

import com.prabin.bootrest.todo.Todo;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoAssert extends AbstractAssert<TodoAssert, Todo> {

	private TodoAssert(Todo actual) {
		super(actual, TodoAssert.class);
	}

	public static TodoAssert assertThatTodo(Todo actual) {
		return new TodoAssert(actual);
	}

	public TodoAssert hasDescription(String expectedDescription) {
		isNotNull();
		String actualDescription = actual.getDescription();
		assertThat(actualDescription).overridingErrorMessage(
				"Expected description to be <%s> but was <%s>",
				expectedDescription, actualDescription).isEqualTo(
				expectedDescription);
		return this;
	}

	public TodoAssert hasId(String expectedId) {
		isNotNull();

		String actualId = actual.getId();
		assertThat(actualId).overridingErrorMessage(
				"Expected id to be <%s> but was <%s>", expectedId, actualId)
				.isEqualTo(expectedId);
		return this;
	}

	public TodoAssert hasTitle(String expectedTitle) {
		isNotNull();
		String actualTitle = actual.getTitle();
		assertThat(actualTitle).overridingErrorMessage(
				"Expected title to be <%s> but was <%s>", expectedTitle,
				actualTitle).isEqualTo(expectedTitle);
		return this;
	}

}
