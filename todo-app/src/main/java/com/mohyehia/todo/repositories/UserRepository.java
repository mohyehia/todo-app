package com.mohyehia.todo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.todo.entities.ApiUser;

@Repository
public interface UserRepository extends MongoRepository<ApiUser, String> {
	ApiUser findByEmail(String email);
}
