package org.example.springproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ApplicationUserDto {

    private String password;

    private String login;

    private String firstName;

    private String lastName;

    private String middleName;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate dateOfBirth;
}
