package com.example.BookMy_SHow.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="theater")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Integer totalScreen;
    private String city;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<Seat> seat;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List <Movie> movie;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List <Screen> screen;


    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List< Booking>booking;



}
