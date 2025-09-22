package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepositery extends JpaRepository<ShowSeat,Long> {
    List<ShowSeat> findByShowId(Long movieId);
    List<ShowSeat>findByShowIdAndStatus(Long showId, String status);

}
