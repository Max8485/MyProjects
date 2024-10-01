package org.example.springproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookDto {

    private Long id;

    private String title;

}
