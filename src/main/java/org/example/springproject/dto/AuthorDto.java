package org.example.springproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorDto {

    private String firstName;

    private String lastName;

    private String middleName;

    @JsonFormat(pattern="dd.MM.yyyy")
    private String dateOfBirth;

    private List<BookDto> books;
}
