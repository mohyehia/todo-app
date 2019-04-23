package com.mohyehia.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.entities.User;
import com.mohyehia.todo.repositories.TodoRepository;

@Service
public class TodoServ {
	@Autowired
	private TodoRepository todoRepository;
	
	public Todo addTodo(Todo todo, User user) {
		todo.setUser(user);
		return todoRepository.save(todo);
	}
	
	public List<Todo> getUserTodos(User user){
		return todoRepository.findByUser(user);
	}
}
