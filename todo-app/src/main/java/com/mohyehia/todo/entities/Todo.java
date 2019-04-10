package com.mohyehia.todo.entities;

import java.util.Date;

public class Todo {
	private int id;
	private String description;
	private Date date;
	private boolean done;
	
	public Todo() {
		
	}
	
	public Todo(int id, String description, Date date, boolean done) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.done = done;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Todo [id=" + id + ", description=" + description + ", date=" + date + ", done=" + done + "]";
	}
	
}
