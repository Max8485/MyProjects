package org.example.springproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate dateOfBirth;

    private List<BookDto> booksList;
}
