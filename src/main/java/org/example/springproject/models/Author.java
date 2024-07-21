package org.example.springproject.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {

    private long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private LocalDate dateOfBirth;

    private List<Book> books;
}
