package com.prabin.bootrest.dto;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static com.prabin.bootrest.dto.TodoAssert.assertThatTodo;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.prabin.bootrest.repository.TodoRepository;
import com.prabin.bootrest.service.TodoService;
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
	public void testCreate_ShouldSaveNewTodoEntry() {
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
	public void testDeleteTodoEntry_ReturnDeletedTodoEntry() throws Exception {
		Todo deleted = new TodoBuilder().id(ID).title(TITLE)
				.description(DESCRIPTION).build();
		
		when(mockTodoRepository.findOne(ID)).thenReturn(Optional.of(deleted));
		
		mongoDBTodoService.delete(ID);
		
		verify(mockTodoRepository, times(1)).delete(deleted);
	}

}
