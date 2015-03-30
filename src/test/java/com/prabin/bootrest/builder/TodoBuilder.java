package com.prabin.bootrest.builder;

import org.springframework.test.util.ReflectionTestUtils;

import com.prabin.bootrest.todo.Todo;

public class TodoBuilder {

	private String description;
	private String id;
	private String title = "NOT_IMPORTANT";

	public TodoBuilder() {
	}

	public TodoBuilder description(String description) {
		this.description = description;
		return this;
	}

	public TodoBuilder id(String id) {
		this.id = id;
		return this;
	}

	public TodoBuilder title(String title) {
		this.title = title;
		return this;
	}

	public Todo build() {
		Todo todo = Todo.getBuilder().description(description).title(title)
				.build();
		ReflectionTestUtils.setField(todo, "id", id);
		return todo;
	}

}
