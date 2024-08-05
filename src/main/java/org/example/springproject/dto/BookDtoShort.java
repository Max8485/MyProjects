package org.example.springproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDtoShort {

    private AuthorDto author;

    private String title;


}
