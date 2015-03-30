package com.prabin.bootrest.dto;

public class TodoNotFoundException extends RuntimeException {
	
	public TodoNotFoundException(String id) {
		super(String.format("No todo entry found with id: ", id));
	}
}
