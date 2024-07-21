package org.example.springproject.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
    private long id;

    private String title;

    private Author author;

    public Book(String title) {
        this.title = title;
    }
}
