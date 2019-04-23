package com.mohyehia.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.todo.entities.User;
import com.mohyehia.todo.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/auth")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody User user){
		if(userService.addUser(user) != null)
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		return new ResponseEntity<>(null, HttpStatus.CONFLICT);
	}
}
