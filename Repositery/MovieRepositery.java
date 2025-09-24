package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Booking;
import com.example.BookMy_SHow.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepositery extends JpaRepository<Movie,Long> {
    Optional<List<Movie>> findByLanguage(String language);

    List<Movie> findByGenre(String genre);
    Optional<Movie>findByTitle(String title);

}
