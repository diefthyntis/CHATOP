package com.diefthyntis.chatop.diefthyntis.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String sentence) {
		super(sentence);
	}
}
