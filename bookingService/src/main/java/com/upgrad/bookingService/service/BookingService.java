package com.upgrad.bookingService.service;

import com.upgrad.bookingService.entity.Booking;
import com.upgrad.bookingService.entity.Transaction;

import java.util.List;

public interface BookingService {
    Booking acceptBooking(Booking booking);

    Booking updateBookingDetails(int id, Booking booking);

    Booking getBookingDetails(int id);

    List<Booking> getAllBookings();

;
}
