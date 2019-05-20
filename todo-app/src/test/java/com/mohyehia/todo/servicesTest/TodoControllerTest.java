package com.mohyehia.todo.servicesTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohyehia.todo.AbstractTodoApiTest;
import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.services.TodoService;

public class TodoControllerTest extends AbstractTodoApiTest {
	
	@MockBean
	private TodoService todoService;
	
	@Test
	public void whenGetAllTodos_returnJsonArray() throws Exception{
		//Mockup data
		Todo todo1 = new Todo("Todo 1", "Todo 1 description", new Date());
		Todo todo2 = new Todo("Todo 2", "Todo 2 description", new Date());
		List<Todo> data = Arrays.asList(todo1, todo2);
		
		//given
		given(todoService.findByUserId(anyString())).willReturn(data);
		
		mockMvc.perform(
				doGet("/api/todos").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));
	}
	
	@Test
	public void whenPostTodo_thenCreateTodo() throws Exception{
		//Mockup data
		Todo todo = new Todo("Todo 1", "Todo 1 description", new Date());
		
		//given
		given(todoService.saveTodo(Mockito.any(Todo.class))).willReturn(todo);
		
		mockMvc.perform(
				doPost("/api/todos").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(todo)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.title", is(todo.getTitle())));
	}
	
	@Test
	public void whenGetTodoById_thenReturnTodo() throws Exception {
		//Mockup data
		Todo todo = new Todo("Todo 1", "Todo 1 description", new Date());
		
		//given
		given(todoService.getTodo(anyString())).willReturn(todo);
		
		mockMvc.perform(
				doGet("/api/todos/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title", is(todo.getTitle())));
	}
}
