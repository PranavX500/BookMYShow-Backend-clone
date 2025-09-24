package com.example.BookMy_SHow.Controller;


import com.example.BookMy_SHow.Dto.MovieDto;
import com.example.BookMy_SHow.Service.MovieService;
import com.example.BookMy_SHow.exception.SeatUnavailableException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {
    @Autowired
    private MovieService movieService;
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto ) throws SeatUnavailableException {
       return new ResponseEntity<>(movieService.createMovie(movieDto),HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<MovieDto>> getBookingAll(){
        List<MovieDto> movies = movieService.getAllMovies();
        if(movies.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @GetMapping("Language/{language}")
    public ResponseEntity<List<MovieDto>>findByLanguage(@PathVariable String language){
        List<MovieDto> languages = movieService.findByLanguage(language);
         if(languages.isEmpty()){
             return ResponseEntity.notFound().build();

         }
         return ResponseEntity.ok(languages);

    }
    @GetMapping("genre/{Genre}")
    public ResponseEntity<List<MovieDto>>findMovieByGenere(@PathVariable String Genre){
        List<MovieDto>Genere=movieService.findMovieByGenere(Genre);
        if(Genere.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Genere);
    }
    @GetMapping("title/{Title}")
    public ResponseEntity<MovieDto>findMovieByTitle(@PathVariable String Title){
        MovieDto title=movieService.getMovieByTitle(Title);
         return ResponseEntity.ok(title);
    }
    @PutMapping("/Update/{id}")
    public ResponseEntity<MovieDto>UpdateById( @PathVariable Long id,@RequestBody MovieDto movieDto){
        MovieDto updatedMovie=movieService.updateById(id,movieDto);
        return ResponseEntity.ok(updatedMovie);


    }
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void>DeleteById(@PathVariable Long id){
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();

    }



}
