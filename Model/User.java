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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private String Password;
    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List< Booking> booking;




}
