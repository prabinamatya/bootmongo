package com.prabin.bootrest.dto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scala.annotation.meta.getter;

import com.prabin.bootrest.repository.TodoRepository;
import com.prabin.bootrest.service.TodoService;
import com.prabin.bootrest.todo.Todo;

@Service
public class MongoDBTodoService implements TodoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBTodoService.class);

    private final TodoRepository todoRepository;
    
    @Autowired
    public MongoDBTodoService(TodoRepository todoRepository) {
    	this.todoRepository = todoRepository;
	}

	@Override
	public TodoDTO create(TodoDTO todo) {
		LOGGER.info("Creating entry");
		Todo persisted  = Todo.getBuilder()
				.title()
		return null;
	}

	@Override
	public TodoDTO delete(String id) {
		return null;
	}

	@Override
	public List<TodoDTO> findAll(String id) {
		return null;
	}

	@Override
	public TodoDTO update(TodoDTO todo) {
		return null;
	}

}
