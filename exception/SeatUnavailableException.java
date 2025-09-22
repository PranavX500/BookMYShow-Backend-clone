package com.example.BookMy_SHow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeatUnavailableException extends Throwable {
    public SeatUnavailableException(String message){
        super(message);
    }
}



