package com.mohyehia.todo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mohyehia.todo.entities.Todo;

@Service
public class TodoService {
	
	private List<Todo> data = new ArrayList<>(Arrays.asList(
		new Todo(1, "Finish Angular", new Date(), false),
		new Todo(2, "Finish Spring Boot", new Date(), false),
		new Todo(3, "Finish MySql", new Date(), false),
		new Todo(4, "Finish Design Patterns", new Date(), false)
	));
	
	public List<Todo> getAll(){
		return data;
	}
	
	public Todo getTodo(int id) {
		for(Todo todo : data)
			if(todo.getId() == id) return todo;
		return null;
	}
	
	public Todo saveTodo(Todo todo) {
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(data.size() + 1);
			data.add(todo);
		} else {
			data.remove(getTodo(todo.getId()));
			data.add(todo);
		}
		return todo;
	}
	
	public void deleteTodo(int id) {
		for(Todo todo : data) {
			if(todo.getId() == id) {
				data.remove(todo);
				return;
			}
		}
	}
	
}
