package com.prabin.bootrest.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.prabin.bootrest.todo.Todo;

public class TodoDTO {

	private String id;
	
	@Size(max = Todo.MAX_LENGTH_DESCRIPTION)
	private String description;
	
	@NotEmpty
	@Size(max = Todo.MAX_LENGTH_TITLE)
	private String title;
	
	public TodoDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "TodoDTO [id=" + id + ", description=" + description
				+ ", title=" + title + "]";
	}
	
	

}
