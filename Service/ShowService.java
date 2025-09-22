package com.example.BookMy_SHow.Service;

import com.example.BookMy_SHow.Dto.*;
import com.example.BookMy_SHow.Model.Movie;
import com.example.BookMy_SHow.Model.Screen;
import com.example.BookMy_SHow.Model.Show;
import com.example.BookMy_SHow.Model.ShowSeat;
import com.example.BookMy_SHow.Repositery.MovieRepositery;
import com.example.BookMy_SHow.Repositery.ScreenRepositery;
import com.example.BookMy_SHow.Repositery.ShowRepositery;
import com.example.BookMy_SHow.Repositery.ShowSeatRepositery;
import com.example.BookMy_SHow.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowRepositery showRepositery;

    @Autowired
    private MovieRepositery movieRepositery;

    @Autowired
    private ScreenRepositery screenRepositery;

    @Autowired
    private ShowSeatRepositery showSeatRepositery;

    public ShowDto createShowDto(ShowDto showDto) {
        Show show = new Show();

        Movie movie = movieRepositery.findById(showDto.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie Not Found"));

        Screen screen = screenRepositery.findById(showDto.getScreen().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen Not Found"));

        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(showDto.getStartTime());
        show.setEndTime(showDto.getEndTime());

        Show savedShow = showRepositery.save(show);

        List<ShowSeat> availableSeats = showSeatRepositery.findByShowIdAndStatus(savedShow.getId(), "AVAILABLE");

        return mapToDto(savedShow, availableSeats);
    }

    public ShowDto getShowById(Long id) {
        Show show = showRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + id));

        List<ShowSeat> availableSeats = showSeatRepositery.findByShowIdAndStatus(id, "AVAILABLE");

        return mapToDto(show, availableSeats);
    }

    public List<ShowDto> getAllShows() {
        List<Show> shows = showRepositery.findAll();

        return shows.stream().map(show -> {
            List<ShowSeat> seats = showSeatRepositery.findByShowId(show.getId());
            return mapToDto(show, seats);
        }).collect(Collectors.toList());
    }

    public List<ShowDto> getShowsBetween(LocalDateTime from, LocalDateTime to) {
        List<Show> shows = showRepositery.findByStartTimeBetween(from, to);

        return shows.stream().map(show -> {
            List<ShowSeat> seats = showSeatRepositery.findByShowId(show.getId());
            return mapToDto(show, seats);
        }).collect(Collectors.toList());
    }

    private ShowDto mapToDto(Show show, List<ShowSeat> availableSeats) {
        ShowDto showDto = new ShowDto();

        showDto.setId(show.getId());
        showDto.setStartTime(show.getStartTime());
        showDto.setEndTime(show.getEndTime());

        // Set Movie details
        showDto.setMovie(new MovieDto(
                show.getMovie().getId(),
                show.getMovie().getTitle(),
                show.getMovie().getDescription(),
                show.getMovie().getLanguage(),
                show.getMovie().getGenre(),
                show.getMovie().getDurationMins(),
                show.getMovie().getReleaseDate(),
                show.getMovie().getPosterUrl()
                ));


        TheaterDto theaterDto = new TheaterDto(
                show.getScreen().getTheater().getId(),
                show.getScreen().getTheater().getName(),
                show.getScreen().getTheater().getAddress(),
                show.getScreen().getTheater().getCity(),
                show.getScreen().getTheater().getTotalScreen()
        );

        showDto.setScreen(new ScreenDto(
                show.getScreen().getId(),
                show.getScreen().getName(),
                show.getScreen().getTotalSeats(),
                theaterDto
        ));


        List<ShowSeatDto> seatDtos = availableSeats.stream()
                .map(seat -> {
                    ShowSeatDto seatDto = new ShowSeatDto();
                    seatDto.setId(seat.getId());
                    seatDto.setStatus(seat.getStatus());
                    seatDto.setPrice(seat.getPrice());

                    SeatDto baseSeatDto = new SeatDto();
                    baseSeatDto.setId(seat.getSeat().getId());
                    baseSeatDto.setSeatNumber(seat.getSeat().getSeatNumber());
                    baseSeatDto.setSeatType(seat.getSeat().getSeatType());
                    baseSeatDto.setBasePrice(seat.getSeat().getBasePrice());

                    seatDto.setSeat(baseSeatDto);

                    return seatDto;
                })
                .collect(Collectors.toList());

        showDto.setAvailableseats(seatDtos);

        return showDto;
    }
}
