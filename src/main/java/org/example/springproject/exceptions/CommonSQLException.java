package org.example.springproject.exceptions;

public class CommonSQLException extends RuntimeException{
    public CommonSQLException(Throwable throwable) {
        super(throwable);
    }
}
