package com.mohyehia.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.todo.entities.Role;
import com.mohyehia.todo.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
