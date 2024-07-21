package org.example.springproject.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Автор не найден");
    }
}
