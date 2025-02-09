package org.maxsid.library.core.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(UserExceptionHandler.class.getName());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LimitBooksForUserException.class)
    public String handleLimitBooksForUserException(LimitBooksForUserException e) {
        LOGGER.error(e.getMessage(), e);
        return e.getMessage();
    }
}
