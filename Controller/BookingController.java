package com.example.BookMy_SHow.Controller;

import com.example.BookMy_SHow.Dto.BookingDto;
import com.example.BookMy_SHow.Dto.BookingRequestDto;
import com.example.BookMy_SHow.Service.BookingService;
import com.example.BookMy_SHow.exception.SeatUnavailableException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> creatingBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) throws SeatUnavailableException {
        BookingDto response = bookingService.createBooking(bookingRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
