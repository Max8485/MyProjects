package org.example.springproject.exceptions;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException() {
        super("Такой автор уже существует");
    }
}
