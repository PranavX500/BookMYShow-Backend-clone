package com.example.BookMy_SHow.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String language;
    private String genere;
    private String durationMins;
    private String ReleaseDate;
    private String posterUrl;

    @OneToMany(mappedBy = "movie",cascade=CascadeType.ALL)
    private List<Show> shows;
}
