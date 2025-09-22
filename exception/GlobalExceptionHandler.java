package com.example.BookMy_SHow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?>resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
    ErrorResponse errorDetails=new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(),"Not Found", ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
}
    @ExceptionHandler(SeatUnavailableException.class)
    public ResponseEntity<?>seatunavailableException(SeatUnavailableException ex, WebRequest request){
        ErrorResponse errorDetails=new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(),"Not Found", ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SeatUnavailableException.class)
    public ResponseEntity<?>globalExceptionalHandler(Exception ex, WebRequest request){
        ErrorResponse errorDetails=new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(),"Not Found", ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
