package com.mohyehia.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.repositories.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> findAll(){
		return todoRepository.findAll();
	}
	
	public Todo getTodo(int id) {
		return todoRepository.findById(id).get();
	}
	
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}
	
	public void deleteTodo(int id) {
		todoRepository.deleteById(id);
	}
	
}
