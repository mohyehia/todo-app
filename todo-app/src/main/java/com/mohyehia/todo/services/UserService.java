package com.mohyehia.todo.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohyehia.todo.entities.Role;
import com.mohyehia.todo.entities.User;
import com.mohyehia.todo.repositories.RoleRepository;
import com.mohyehia.todo.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	private boolean exists(User user) {
		return findByEmail(user.getEmail()) != null;
	}
	
	public User addUser(User user) {
		if(exists(user)) return null;
		user.setActive(1);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName("USER");
		user.setRoles(new HashSet<>(Arrays.asList(role)));
		return userRepository.save(user);
	}
}
