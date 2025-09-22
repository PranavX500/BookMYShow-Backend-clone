package com.example.BookMy_SHow.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Show")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name="Movies_id",nullable = false)
    private  Movie movie;

    @ManyToOne
    @JoinColumn(name="Movies_id",nullable = false)
    private  Screen screen;

    @OneToMany(mappedBy = "Show",cascade = CascadeType.ALL)
    private List<Seat> showSeats;

    @OneToMany(mappedBy = "Show",cascade = CascadeType.ALL)
    private List<Booking> bookings;






}
