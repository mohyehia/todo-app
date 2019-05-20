package com.mohyehia.todo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.todo.entities.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{
	Todo findByTitle(String title);
	
	List<Todo> findByUserId(String userId);
}
