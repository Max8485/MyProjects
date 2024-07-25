package org.example.springproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.springproject.models.Book;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {

    private String firstName;

    private String lastName;

    private String middleName;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate dateOfBirth;

    private List<BookDto> books;
}
