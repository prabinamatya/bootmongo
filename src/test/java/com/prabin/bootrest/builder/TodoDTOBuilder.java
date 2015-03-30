package com.prabin.bootrest.builder;

import com.prabin.bootrest.dto.TodoDTO;

public class TodoDTOBuilder  {
	
	private String description;
	private String id;
	private String title;
	
	public TodoDTOBuilder() {
	}
	
	public TodoDTOBuilder description(String description) {
		this.description = description;
		return this;
	}
	
	public TodoDTOBuilder id(String id) {
		this.id = id;
		return this;
	}
	
	public TodoDTOBuilder title(String title) {
		this.title = title;
		return this;
	}
	
	public TodoDTO build() {
		TodoDTO dto = new TodoDTO();
		dto.setDescription(description);
		dto.setId(id);
		dto.setTitle(title);
		return dto;
	}
	
}
