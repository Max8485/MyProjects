package org.maxsid.library.core.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Книга не найдена");
    }
}
