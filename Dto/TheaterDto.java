package com.example.BookMy_SHow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private Integer totalScreen;

}
