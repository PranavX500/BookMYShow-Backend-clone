package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepositery extends JpaRepository<Theater,Long> {
    List<Theater> findByShowId(String city);
}
