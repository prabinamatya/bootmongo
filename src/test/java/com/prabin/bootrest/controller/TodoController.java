package com.prabin.bootrest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prabin.bootrest.dto.MongoDBTodoService;
import com.prabin.bootrest.dto.TodoDTO;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private final MongoDBTodoService service;
    
    @Autowired
    public TodoController(MongoDBTodoService service) {
    	this.service = service;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDTO create(@RequestBody @Valid TodoDTO todoEntry) {
    	LOGGER.info("Creating a new todo enetry with information: ", todoEntry);
    	
    	TodoDTO created = service.create(todoEntry);
    	
    	LOGGER.info("Created a new todo entry with info", todoEntry);
    	return created;
    }
    
}
