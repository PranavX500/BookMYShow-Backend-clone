package com.example.BookMy_SHow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private Integer DurationMins;
    private String releaseDate;
    private String posterUrl;

}
