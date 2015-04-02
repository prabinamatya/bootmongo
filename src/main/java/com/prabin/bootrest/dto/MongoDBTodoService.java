package com.prabin.bootrest.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
				.title(todo.getTitle())
				.description(todo.getDescription())
				.build();
		
		persisted = todoRepository.save(persisted);
		LOGGER.info("Created new todo entry ");
		return convertToDTO(persisted);
	}

	private TodoDTO convertToDTO(Todo model) {
		TodoDTO dto = new TodoDTO();
		dto.setId(model.getId());
		dto.setTitle(model.getTitle());
		dto.setDescription(model.getDescription());
		return dto;
	}

	@Override
	public TodoDTO delete(String id) {
		LOGGER.info("Deleting a todo entry with id: ", id);
		
		Todo deleted = findTodoByid(id);
		todoRepository.delete(deleted);
		
		LOGGER.info("Deleted entry with id: ", id);
		return convertToDTO(deleted);
	}

	private Todo findTodoByid(String id) {
		Optional<Todo> result = todoRepository.findOne(id);
		return result.orElseThrow(() -> new TodoNotFoundException(id));
	}

	@Override
	public List<TodoDTO> findAll() {
		LOGGER.info("finding all todo entries");
		
		List<Todo> todoEntries = todoRepository.findAll();
		
		LOGGER.info("Found todo entries of size", todoEntries.size());
		return convertToDTOs(todoEntries);
	}

	private List<TodoDTO> convertToDTOs(List<Todo> models) {
		return models.stream()
				.map(this::convertToDTO)
				.collect(toList());
	}


	@Override
	public TodoDTO update(TodoDTO todo) {
		LOGGER.info("Updating todo entry with info", todo);
		
		Todo updated = findTodoByid(todo.getId());
		updated.update(todo.getTitle(), todo.getDescription());
		updated = todoRepository.save(updated);
		
		LOGGER.info("Updated todo entry with info", updated);
		return convertToDTO(updated);
	}

	@Override
	public TodoDTO findById(String id) {
		LOGGER.info("finding todo by id", id);
		
		Todo found = findTodoByid(id);
		
		LOGGER.info("Found todo entry ", found);
		
		return convertToDTO(found);
	}

}
