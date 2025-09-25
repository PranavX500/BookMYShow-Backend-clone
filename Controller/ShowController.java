package com.example.BookMy_SHow.Controller;

import com.example.BookMy_SHow.Dto.ShowDto;
import com.example.BookMy_SHow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/Show")
public class ShowController {
   @Autowired
   ShowService showService;
    @PostMapping("/CreateShow")
    public ResponseEntity<ShowDto>createShowDto(@RequestBody ShowDto showDto){
        ShowDto response= showService.createShowDto(showDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/ShowId/{id}")
    public ResponseEntity<ShowDto>getShowById(Long id){
        return ResponseEntity.ok(showService.getShowById(id));
    }
    @GetMapping()
    public ResponseEntity<List<ShowDto>>getAllShows(){
        List<ShowDto>showDtoList=showService.getAllShows();
        if(showDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(showDtoList);
    }
    @GetMapping("/ShowsTime/{start}/{end}")
    public ResponseEntity<List<ShowDto>> getShowBetween(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        List<ShowDto> shows = showService.getShowsBetween(start.atStartOfDay(), end.atStartOfDay());
        return ResponseEntity.ok(shows);
    }






}
