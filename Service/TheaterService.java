package com.example.BookMy_SHow.Service;

import com.example.BookMy_SHow.Dto.TheaterDto;
import com.example.BookMy_SHow.Model.Theater;
import com.example.BookMy_SHow.Repositery.TheaterRepositery;
import com.example.BookMy_SHow.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    private final TheaterRepositery theaterRepositery;

    public TheaterService(TheaterRepositery theaterRepositery) {
        this.theaterRepositery = theaterRepositery;
    }

    public TheaterDto createTheater(TheaterDto theaterDto){
        Theater theater = mapToEntity(theaterDto);
        Theater savedTheater = theaterRepositery.save(theater);
        return mapToDto(savedTheater);
    }
    public TheaterDto updateById(Long id, TheaterDto theaterDto) {
        Theater theater = theaterRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find theater with ID " + id + " to update"));

        theater = mapToUpdatingEntity(theater, theaterDto);

        Theater updatedTheater = theaterRepositery.save(theater);

        return mapToDto(updatedTheater);
    }

    public void deleteTheaterById(Long id) {
        if (!theaterRepositery.existsById(id)) {
            throw new RuntimeException("Theater not found with ID: " + id);
        }
        theaterRepositery.deleteById(id);
    }

    private Theater mapToUpdatingEntity(Theater theater, TheaterDto theaterDto) {
        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreen(theaterDto.getTotalScreen());
        return theater;
    }




    public List<TheaterDto> getAllTheaters() {
        List<Theater> theaters = theaterRepositery.findAll();
        return theaters.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    public List<TheaterDto> getTheatersByCity(String city) {
        List<Theater> theaters = theaterRepositery.findByCity(city);
        return theaters.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private TheaterDto mapToDto(Theater theater){
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setName(theater.getName());
        theaterDto.setCity(theater.getCity());
        theaterDto.setAddress(theater.getAddress());
        theaterDto.setTotalScreen(theater.getTotalScreen());
        return theaterDto;
    }

    private Theater mapToEntity(TheaterDto theaterDto){
        Theater theater = new Theater();
        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreen(theaterDto.getTotalScreen());
        return theater;
    }
}