package org.example.springproject.exceptions;

public class IncorrectLoginAndPasswordException extends RuntimeException {
    public IncorrectLoginAndPasswordException() {
        super("Неправильный логин или пароль");
    }
}
