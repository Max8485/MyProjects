package org.example.springproject.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(AuthorExceptionHandler.class.getName());
    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public String handleAuthorAlreadyExists(AuthorAlreadyExistsException e) {
        LOGGER.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AuthorNotFoundException.class)
    public String handleAuthorNotFoundException(AuthorNotFoundException e) {
        LOGGER.error(e.getMessage(), e);
        return e.getMessage();
    }
}
