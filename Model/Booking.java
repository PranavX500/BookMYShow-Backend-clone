package com.example.BookMy_SHow.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String bookingNumber;
    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @ManyToOne
    @Column(name="Show_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double totalAmoumnt;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
    private List<Seat>ShowSeat;

    @OneToOne(cascade = CascadeType.ALL)
    @ JoinColumn(name="payment_id")
    private Payment payment;


}
