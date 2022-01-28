package com.upgrad.bookingService.service;

import com.upgrad.bookingService.entity.Booking;
import com.upgrad.bookingService.entity.Transaction;
import com.upgrad.bookingService.Dto.TransactionInfoDto;
import com.upgrad.bookingService.exception.BookNotFoundException;
import com.upgrad.bookingService.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.Temporal;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;

    public static ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }


    @Override
    public Booking acceptBooking(Booking booking) {
        Booking savedBooking = new Booking();

        savedBooking.setBookedOn(booking.getBookedOn());

        savedBooking.setTransactionId(booking.getTransactionId());
        savedBooking.setFromDate(booking.getFromDate());
        savedBooking.setToDate(booking.getToDate());
        savedBooking.setAadharNumber(booking.getAadharNumber());
        Long diff = booking.getToDate().getTime() - booking.getFromDate().getTime();

        int days = (int) (diff / (1000 * 60 * 60 * 24));
        savedBooking.setRoomPrice((int) (1000 * booking.getNumOfRooms() * days));
        savedBooking.setNumOfRooms(booking.getNumOfRooms());
        ArrayList<String> array = getRandomNumbers(booking.getNumOfRooms());
        String number = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            number += "," + array.get(i);
        }

        savedBooking.setRoomNumbers(number);
        savedBooking.setBookedOn(new Date());
        return bookingRepository.save(savedBooking);
    }

    @Override
    public Booking getBookingDetails(int id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookNotFoundException("this error"));
    }

    @Override
    public Booking updateBookingDetails(int id, Booking booking) {
        Booking updateBooking = getBookingDetails(id);
        Long diff = booking.getFromDate().getTime() - booking.getToDate().getTime();
        long noOfDaysBetween = DAYS.between((Temporal) booking.getFromDate(), (Temporal) booking.getToDate());
        updateBooking.setNumOfRooms(booking.getNumOfRooms());
        updateBooking.setBookedOn(booking.getBookedOn());
        updateBooking.setNumOfRooms(booking.getBookingId());
        updateBooking.setAadharNumber(booking.getAadharNumber());
        updateBooking.setRoomNumbers(booking.getRoomNumbers());
        int roomChangePrice = 1000 * booking.getNumOfRooms();
        updateBooking.setRoomPrice(roomChangePrice);// room needs to change

        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return null;
    }


}

