package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Movie;
import com.example.BookMy_SHow.Model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreenRepositery extends JpaRepository<Screen,Long> {
    List<Screen> findByTheater_Id(Long theaterId);




}
