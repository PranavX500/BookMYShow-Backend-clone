package com.example.BookMy_SHow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.function.LpadRpadPadEmulation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    private Long id;
    private String seatNumber;
    private String seatType;
    private double basePrice;

}
