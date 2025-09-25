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

    @PostMapping("/create")
    public ResponseEntity<BookingDto> creatingBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) throws SeatUnavailableException {
        BookingDto response = bookingService.createBooking(bookingRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto>getBookingById(@PathVariable Long id ){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
    @GetMapping("/number/{bookingNumber}")
    public ResponseEntity<BookingDto> getBookingByNumber(@PathVariable String bookingNumber) {
        return ResponseEntity.ok(bookingService.getBookingByNumber(bookingNumber));
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<BookingDto> cancelBooking(@PathVariable Long id) {
        BookingDto cancelledBooking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(cancelledBooking);
    }

}
