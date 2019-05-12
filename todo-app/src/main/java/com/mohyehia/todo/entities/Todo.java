package com.mohyehia.todo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name="TODOS")
public class Todo {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;
	private boolean done;

	public Todo() {
		
	}
	
	public Todo(String title, String description, Date date) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.done = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", done="
				+ done + "]";
	}
	
}
