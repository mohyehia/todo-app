package com.mohyehia.todo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.exceptions.ConflictException;
import com.mohyehia.todo.exceptions.NotFoundException;
import com.mohyehia.todo.repositories.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> findByUserId(String userId){
		return todoRepository.findByUserId(userId);
	}
	
	public List<Todo> findAll(){
		return todoRepository.findAll();
	}
	
	public Todo getTodo(String id) {
		try {
			return todoRepository.findById(id).get();			
		} catch (NoSuchElementException e) {
			throw new NotFoundException(String.format("No record with id [%s] was found in our database.", id));
		}
	}
	
	public Todo saveTodo(Todo todo) {
		if(todoRepository.findByTitle(todo.getTitle()) != null)
			throw new ConflictException("Another todo with the same title exists!");
		return todoRepository.save(todo);
	}
	
	public void deleteTodo(String id) {
		todoRepository.deleteById(id);
	}
	
}
