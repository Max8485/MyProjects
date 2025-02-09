package org.maxsid.library.core.exceptions;

public class LimitBooksForUserException extends RuntimeException{

    public LimitBooksForUserException() {
        super("Пользователь не может взять больше 3 книг");
    }
}
