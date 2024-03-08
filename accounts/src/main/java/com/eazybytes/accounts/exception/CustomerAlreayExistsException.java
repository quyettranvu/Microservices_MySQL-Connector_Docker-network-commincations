package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreayExistsException extends RuntimeException {
    
    public CustomerAlreayExistsException(String message) {
        super(message);
    }
}
