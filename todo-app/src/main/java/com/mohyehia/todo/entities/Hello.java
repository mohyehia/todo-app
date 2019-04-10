package com.mohyehia.todo.entities;

public class Hello {
	private String message;
	
	public Hello(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[message = %s]", message);
	}
}
