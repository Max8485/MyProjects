package org.example.springproject.dto;

import lombok.*;
import org.example.springproject.models.Author;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDtoShort {

    private AuthorDto author;

    private String title;


}
