package org.example.springproject.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class Book {
    private long id;

    private String title;

    private Author author;
}
