package com.mohyehia.todo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mohyehia.todo.entities.ApiUser;

public abstract class BaseController {
	
	public ApiUser getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (ApiUser) authentication.getPrincipal();
	}
	
}
