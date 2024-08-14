package com.diefthyntis.chatop.diefthyntis.exception;

public class MissingFileException extends RuntimeException {
	  private static final long serialVersionUID = 1L;
	    public MissingFileException(String sentence) {
	        super(sentence);
	    }
}
