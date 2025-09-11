package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Booking;
import com.example.BookMy_SHow.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepositery extends JpaRepository<Movie,Long> {
    List<Movie> findByLanguage(String language);

    Optional<Movie> findByBookingNumber(String genre);

    List<Movie>findByTitle(String title);
}
