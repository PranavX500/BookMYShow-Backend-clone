package com.example.BookMy_SHow.Controller;

import com.example.BookMy_SHow.Dto.TheaterDto;
import com.example.BookMy_SHow.Service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }


    @PostMapping
    public ResponseEntity<TheaterDto> createTheater(@RequestBody TheaterDto theaterDto) {
        TheaterDto createdTheater = theaterService.createTheater(theaterDto);
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<TheaterDto> updateTheater(@PathVariable Long id, @RequestBody TheaterDto theaterDto) {
        TheaterDto updatedTheater = theaterService.updateById(id, theaterDto);
        return ResponseEntity.ok(updatedTheater);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheaterById(id);
        return ResponseEntity.ok("Theater deleted successfully with ID: " + id);
    }


    @GetMapping
    public ResponseEntity<List<TheaterDto>> getAllTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }


    @GetMapping("/city/{city}")
    public ResponseEntity<List<TheaterDto>> getTheatersByCity(@PathVariable String city) {
        return ResponseEntity.ok(theaterService.getTheatersByCity(city));
    }
}
