package org.maxsid.library.core.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Автор не найден");
    }
}
