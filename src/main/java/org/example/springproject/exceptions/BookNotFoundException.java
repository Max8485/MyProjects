package org.example.springproject.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Книга не найдена");
    }
}
