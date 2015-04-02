package com.prabin.bootrest.dto;

import static com.prabin.bootrest.dto.TodoAssert.assertThatTodo;
import static com.prabin.bootrest.dto.TodoDTOAssert.assertThatTodoDTO;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;



import static org.assertj.core.api.Assertions.assertThat;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.findShortestPaths;


import org.springframework.test.util.ReflectionTestUtils;

import com.prabin.bootrest.builder.TodoBuilder;
import com.prabin.bootrest.builder.TodoDTOBuilder;
import com.prabin.bootrest.repository.TodoRepository;
import com.prabin.bootrest.todo.Todo;

@RunWith(MockitoJUnitRunner.class)
public class MongoDBTodoServiceTest {

	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String ID = "id";

	@Mock
	private TodoRepository mockTodoRepository;

	private MongoDBTodoService mongoDBTodoService;

	@Before
	public void setUp() {
		this.mongoDBTodoService = new MongoDBTodoService(mockTodoRepository);
	}

	@Test
	public void create_ShouldSaveNewTodoEntry() {
		TodoDTO newTodo = new TodoDTOBuilder().title(TITLE)
				.description(DESCRIPTION).id(ID).build();

		when(mockTodoRepository.save(isA(Todo.class))).thenAnswer(
				invocation -> (Todo) invocation.getArguments()[0]);

		mongoDBTodoService.create(newTodo);

		ArgumentCaptor<Todo> savedTodoArgument = ArgumentCaptor
				.forClass(Todo.class);
		verify(mockTodoRepository, times(1)).save(savedTodoArgument.capture());
		verifyNoMoreInteractions(mockTodoRepository);

		Todo savedTodo = savedTodoArgument.getValue();
		assertThatTodo(savedTodo).hasTitle(TITLE).hasDescription(DESCRIPTION);
	}
	
	@Test
	public void create_ReturnsInfoOfCreatedTodoEntry() throws Exception {
		TodoDTO newTodo = new TodoDTOBuilder().title(TITLE).description(DESCRIPTION).build();
		
		when(mockTodoRepository.save(isA(Todo.class))).thenAnswer(invocation -> {
			Todo persisted = (Todo) invocation.getArguments()[0];
			ReflectionTestUtils.setField(persisted, "id", ID);
			return persisted;
		});
		
		TodoDTO returned = mongoDBTodoService.create(newTodo);
		assertThatTodoDTO(returned).hasId(ID).hasTitle(TITLE).hasDescription(DESCRIPTION);
	}

	@Test
	public void delete_DeleteTodoEntry_ReturnDeletedTodoEntry() throws Exception {
		Todo deleted = new TodoBuilder().id(ID).title(TITLE)
				.description(DESCRIPTION).build();
		
		when(mockTodoRepository.findOne(ID)).thenReturn(Optional.of(deleted));
		
		mongoDBTodoService.delete(ID);
		
		verify(mockTodoRepository, times(1)).delete(deleted);
	}
	
	@Test
	public void delete_DeleteAndReturnTheDeletedTodoEntry() throws Exception {
		Todo deleted = new TodoBuilder().id(ID).title(TITLE).description(DESCRIPTION).build();
		
		when(mockTodoRepository.findOne(ID)).thenReturn(Optional.of(deleted));
		
		TodoDTO returned = mongoDBTodoService.delete(ID);
		
		assertThatTodoDTO(returned).hasId(ID).hasTitle(TITLE).hasDescription(DESCRIPTION);
	}
	
	@Test
	public void findAll_ReturnOneTodoEntry() throws Exception {
		Todo expected = new TodoBuilder().id(ID).title(TITLE).description(DESCRIPTION).build();
		
		when(mockTodoRepository.findAll()).thenReturn(Arrays.asList(expected));
		
		List<TodoDTO> todoEntries = mongoDBTodoService.findAll();
		assertThat(todoEntries).hasSize(1);
		
		TodoDTO actual = todoEntries.iterator().next();
		assertThatTodoDTO(actual).hasId(ID).hasTitle(TITLE).hasDescription(DESCRIPTION);
	}
	
	@Test
	public void findById_ReturnsFoundTodoEntry() throws Exception {
		Todo found = new TodoBuilder().id(ID).description(DESCRIPTION).title(TITLE).build();
		
		when(mockTodoRepository.findOne(ID)).thenReturn(Optional.of(found));
		
		TodoDTO returned = mongoDBTodoService.findById(ID);
		
		assertThatTodoDTO(returned).hasId(ID).hasTitle(TITLE).hasDescription(DESCRIPTION);
	}
	
	@Test(expected = TodoNotFoundException.class)
	public void findById_IfNotFoundThrowException() throws Exception {
		when(mockTodoRepository.findOne(ID)).thenReturn(Optional.empty());
		
		mongoDBTodoService.findById(ID);
	}

}
