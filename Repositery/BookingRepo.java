package com.example.BookMy_SHow.Repositery;

import com.example.BookMy_SHow.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findByUserId(Long userId);

    Optional<Booking> findByBookingNumber(String bookingNumber);

    List<Booking> findByShow_Id(Long id);

}
