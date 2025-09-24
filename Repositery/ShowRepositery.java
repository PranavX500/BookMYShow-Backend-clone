package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Movie;
import com.example.BookMy_SHow.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShowRepositery extends JpaRepository<Show,Long> {
    List<Show> findByMovie_Id(Long movieId);

    List<Show> findByScreen_Id(Long screenId);

    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Show> findByMovie_IdAndScreen_Theater_City(Long movieId, String city);






}
