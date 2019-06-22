package com.mohyehia.todo.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;

@Document
public class Todo {
	@Id
	private String id;
	
	@ApiModelProperty(value = "Descriptive title for the todo")
	@NotNull(message = "Title is required")
	@Size(min = 3, message = "Title must be at least 3 characters long")
	private String title;
	
	@ApiModelProperty(value = "Description or content of the created todo")
	@NotNull(message = "description is required")
	private String description;
	
	@ApiModelProperty(value = "Created date of this todo")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	private boolean done;
	
	@ApiModelProperty(value = "Id of the user who is creating the todo")
	private String userId;

	public Todo() {
		
	}
	
	public Todo(String title, String description, Date date) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.done = false;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", done="
				+ done + ", userId=" + userId + "]";
	}
	
}
