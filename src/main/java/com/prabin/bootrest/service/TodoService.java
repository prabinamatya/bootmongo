package com.prabin.bootrest.service;

import java.util.List;

import com.prabin.bootrest.dto.TodoDTO;

public interface TodoService {

	TodoDTO create(TodoDTO todo);
	
	TodoDTO delete(String id);
	
	List<TodoDTO> findAll();
	
	TodoDTO findById(String id);
	
	TodoDTO update(TodoDTO todo);
}
