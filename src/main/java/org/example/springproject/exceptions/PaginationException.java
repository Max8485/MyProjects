package org.example.springproject.exceptions;

public class PaginationException extends RuntimeException{
    public PaginationException() {
        super("Превышен лимит страниц!");
    }
}
