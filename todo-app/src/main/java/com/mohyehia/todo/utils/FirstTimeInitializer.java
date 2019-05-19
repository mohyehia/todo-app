package com.mohyehia.todo.utils;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mohyehia.todo.entities.ApiUser;
import com.mohyehia.todo.services.UserService;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

	private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * check if there are users in the app
		 * if not create new user
		 * else do nothing
		 */
		if(userService.findAll().isEmpty()) {
			logger.info("No user accouns found. Creating some user accounts...");
			
			ApiUser user = new ApiUser("moh@mail.com", "0000", "Mohammed");
			userService.save(user);
		}
		
	}

}
