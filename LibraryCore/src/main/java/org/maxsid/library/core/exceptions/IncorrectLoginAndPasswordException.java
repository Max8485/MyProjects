package org.maxsid.library.core.exceptions;

public class IncorrectLoginAndPasswordException extends RuntimeException {
    public IncorrectLoginAndPasswordException() {
        super("Неправильный логин или пароль");
    }
}
