package com.example.BookMy_SHow.Service;

import com.example.BookMy_SHow.Dto.MovieDto;
import com.example.BookMy_SHow.Model.Movie;
import com.example.BookMy_SHow.Repositery.MovieRepositery;
import com.example.BookMy_SHow.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class MovieService {
    @Autowired
    private   MovieRepositery movieRepositery;



      public MovieDto createMovie(MovieDto movieDto){
          Movie movie=maptoEntity(movieDto);
          movie=movieRepositery.save(movie);
          return mapToDto(movie);


        }
    public MovieDto getMovieById(Long id) {
        Movie movie =  movieRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return mapToDto(movie);
    }
    public List<MovieDto> getAllMovies() {
        List<Movie> movies =movieRepositery.findAll();
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    public List<MovieDto> findByLanguage(String language) {
          List<Movie> movie=movieRepositery.findByLanguage(language)
                  .orElseThrow(() -> new ResourceNotFoundException("movie not found with"+language));
       return movie.stream()
               .map(this::mapToDto)
               .collect(Collectors.toList());

    }
    public List<MovieDto>getMovieByGener(String genre) {
          List<Movie> movie=movieRepositery.findByGenre(genre);
          if(movie.isEmpty()) {
              throw new ResourceNotFoundException("Movie not found with" +genre);
          }
          return movie.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    public MovieDto getMovieByTitle(String title) {
        Movie movie = movieRepositery.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with title: " + title));

        return mapToDto(movie);
    }

    public MovieDto updateById(Long id,MovieDto movieDto ){
          Movie movie=movieRepositery.findById(id)
                  .orElseThrow(()->new ResourceNotFoundException("Cant find the movie id so cant update"+id));
        movie = maptoUpdatingEntity(movie, movieDto);

        movie = movieRepositery.save(movie);

        return mapToDto(movie);



    }

    public void deleteMovieById(Long id) {
        boolean exists = movieRepositery.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Movie not found with id: " + id);
        }
        movieRepositery.deleteById(id);
    }

    private Movie maptoUpdatingEntity( Movie movie,MovieDto movieDto){

        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setLanguage(movieDto.getGenre());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());
        return movie;

    }


    private Movie maptoEntity(MovieDto movieDto){
          Movie movie=new Movie();
          movie.setTitle(movieDto.getTitle());
          movie.setDescription(movieDto.getDescription());
          movie.setLanguage(movieDto.getGenre());
          movie.setDurationMins(Integer.valueOf(String.valueOf(movieDto.getDurationMins())));
          movie.setReleaseDate(LocalDate.parse(String.valueOf(movieDto.getReleaseDate())));
          movie.setPosterUrl(movieDto.getPosterUrl());
          return movie;



        }
    private MovieDto mapToDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setLanguage(movie.getLanguage());
        dto.setDurationMins(Integer.valueOf(movie.getDurationMins()));
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setGenre(movie.getGenre());
        return dto;
    }

    }


