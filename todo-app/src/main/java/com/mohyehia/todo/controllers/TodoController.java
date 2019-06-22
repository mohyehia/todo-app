package com.mohyehia.todo.controllers;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/todos")
public class TodoController extends BaseController {
	@Autowired
	private TodoService todoService;
	
	@ApiOperation(value = "Get user todos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully get all user todos")
	})
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<Todo>> getAll() {		
		return new ResponseEntity<>(todoService.findByUserId(getCurrentUser().getId()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get todo by id")
	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable String id) {
		return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add new todo")
	@PostMapping(value = {"", "/"})
	public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo todo) {
		todo.setUserId(getCurrentUser().getId());
		return new ResponseEntity<>(todoService.saveTodo(todo), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update existing todo by id")
	@PutMapping("/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String id, @Valid @RequestBody Todo todo) {
		todo.setId(id);
		return new ResponseEntity<Todo>(todoService.saveTodo(todo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete existing todo by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
		todoService.deleteTodo(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
