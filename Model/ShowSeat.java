package com.example.BookMy_SHow.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;

@Entity
@Table(name="show_seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="shows_id",nullable = false)
    private Show show;

    @ManyToOne
    @JoinColumn(name="Seat_id",nullable = false)
    private Seat seat;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name="Booking_id")
    private Booking booking;


}
