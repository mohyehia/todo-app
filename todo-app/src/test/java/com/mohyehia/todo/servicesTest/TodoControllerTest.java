package com.mohyehia.todo.servicesTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohyehia.todo.TodoAppApplication;
import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.services.TodoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TodoAppApplication.class)
@AutoConfigureMockMvc
public class TodoControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TodoService todoService;
	
	@Test
	public void whenGetAllTodos_returnJsonArray() throws Exception{
		//Mockup data
		Todo todo1 = new Todo("Todo 1", "Todo 1 description", new Date());
		Todo todo2 = new Todo("Todo 2", "Todo 2 description", new Date());
		List<Todo> data = Arrays.asList(todo1, todo2);
		
		//given
		given(todoService.findAll()).willReturn(data);
		
		mockMvc.perform(
				get("/api/todos").contentType(MediaType.APPLICATION_JSON))
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
				post("/api/todos").contentType(MediaType.APPLICATION_JSON)
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
				get("/api/todos/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title", is(todo.getTitle())));
	}
}
