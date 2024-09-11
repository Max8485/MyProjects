package org.example.springproject;

import org.example.springproject.entity.Author;
import org.example.springproject.entity.Book;

import java.time.LocalDate;

public class TestDataProvider {

    public static Author buildAuthor(int index) {
        return Author.builder()
                .firstName("TestFirstName" + index)
                .lastName("TestLastName" + index)
                .middleName("TestMiddleName" + index)
                .dateOfBirth(LocalDate.now())
                .build();
    }

    public static Book buildBook(int index) {
        return Book.builder()
                .title("TestTitle" + index)
                .build();
    }
}
