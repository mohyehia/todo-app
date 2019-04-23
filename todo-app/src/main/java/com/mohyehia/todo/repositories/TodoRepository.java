package com.mohyehia.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.todo.entities.Todo;
import com.mohyehia.todo.entities.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUser(User user);
}
