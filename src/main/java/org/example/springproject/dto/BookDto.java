package org.example.springproject.dto;

import lombok.*;
import org.example.springproject.models.Author;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {

    private Long id;

    private String title;

}
