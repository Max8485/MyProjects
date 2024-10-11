package org.maxsid.library.core.exceptions;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException() {
        super("Такой автор уже существует");
    }
}
