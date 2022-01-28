package com.upgrad.bookingService.exception.handler;

import com.upgrad.bookingService.exception.BookNotFoundException;
import com.upgrad.bookingService.exception.InvalidCardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCardException.class)
    public final ResponseEntity<Object> handleInvalidCardException(InvalidCardException e, WebRequest req) {
        return new ResponseEntity<Object>(new ApiError("Invalid mode of payment", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<Object> handleBookNotException(InvalidCardException e, WebRequest req) {
        return new ResponseEntity<Object>(new ApiError(" Invalid Booking Id ", 400), HttpStatus.BAD_REQUEST);
    }
}

