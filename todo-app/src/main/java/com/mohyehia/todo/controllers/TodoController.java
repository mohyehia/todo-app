package com.mohyehia.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.services.TodoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/todos")
public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<Todo>> getAll() {
		return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable int id) {
		return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
	}
	
	@PostMapping(value = {"", "/"})
	public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
		return new ResponseEntity<>(todoService.saveTodo(todo), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo todo) {
		todo.setId(id);
		return new ResponseEntity<Todo>(todoService.saveTodo(todo), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
		todoService.deleteTodo(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
