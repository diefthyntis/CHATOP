package com.diefthyntis.chatop.diefthyntis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email Adress already exists.")
public class EmailAddressAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public EmailAddressAlreadyExistsException(String message) {
        super(message);
    }
}