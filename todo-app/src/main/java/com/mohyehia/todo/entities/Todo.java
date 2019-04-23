package com.mohyehia.todo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TODOS")
public class Todo {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String description;
	private Date date;
	private boolean done;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public Todo() {
		
	}
	
	public Todo(String description, Date date, boolean done) {
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", date=" + date + ", done=" + done + ", user="
				+ user + "]";
	}
	
}
