package com.example.BookMy_SHow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private String language;
    private String description;
    private String genre;
    private Integer durationMins;
    private LocalDate ReleaseDate;
    private String posterUrl;



}
