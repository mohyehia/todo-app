package com.mohyehia.todo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.todo.entities.Hello;

@CrossOrigin(origins = "http://localhost:4200")
@RestController(value = "hello")
public class HelloService {
	@GetMapping
	public Hello hello(String message) {
		return new Hello("Mohammed");
	}
}
