package com.mohyehia.todo.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.exceptions.NotFoundException;
import com.mohyehia.todo.repositories.TodoRepository;
import com.mohyehia.todo.services.TodoService;

@RunWith(SpringRunner.class)
public class TodoServiceTest {
	
	@MockBean
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoService todoService;
		
	@TestConfiguration
	static class TodoServiceContextConfiguration{
		@Bean
		public TodoService todoService() {
			return new TodoService();
		}
	}
	
	@Test
	public void whenFindAll_returnTodosList() {
		//Mockup data
		Todo todo1 = new Todo("Todo 1", "Todo 1 description", new Date());
		Todo todo2 = new Todo("Todo 2", "Todo 2 description", new Date());
		List<Todo> data = Arrays.asList(todo1, todo2);
		
		//given
		given(todoRepository.findAll()).willReturn(data);
		
		//assertion
		assertThat(todoService.findAll())
			.hasSize(2)
			.contains(todo1, todo2);
	}
	
	@Test
	public void whenGetById_returnTodo() {
		//Mockup data
		Todo todo = new Todo("Todo 1", "Todo 1 description", new Date());
		
		//given
		given(todoRepository.findById(anyString())).willReturn(Optional.ofNullable(todo));
		
		//assertion
		assertThat(todo.getTitle()).containsIgnoringCase("todo");
	}
	
	@Test(expected = NotFoundException.class)
	public void whenInvalidId_TodoNotFound() {
		//given
		given(todoRepository.findById(anyString())).willReturn(Optional.empty());
		
		todoService.getTodo("1");
	}
	
	@Test
	public void whenSaveTodo_thenCreateTodo() {
		//Mockup data
		Todo todo = new Todo("Todo 1", "Todo 1 description", new Date());
		
		//given
		given(todoRepository.save(Mockito.any(Todo.class))).willReturn(todo);
		
		Todo result = todoRepository.save(todo);
		
		assertThat(result.getTitle()).isEqualTo(todo.getTitle());
	}

}
