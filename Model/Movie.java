package com.example.BookMy_SHow.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Title;
    private String language;
    private String genre;
    private Integer durationMins;
    private LocalDate ReleaseDate;
    private String posterUrl;
    private String Description;

    @OneToMany(mappedBy = "movie",cascade=CascadeType.ALL)
    private List<Show> shows;


}
