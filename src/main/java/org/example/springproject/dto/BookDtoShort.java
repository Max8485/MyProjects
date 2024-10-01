package org.example.springproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookDtoShort {
    private Long id;

    private AuthorDto author;

    private String title;


}
