package com.diefthyntis.chatop.diefthyntis.exception;

public class RentalNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RentalNotFoundException(String sentence) {
		super(sentence);
	}
}
