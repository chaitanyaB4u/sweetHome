package com.upgrad.bookingService.Controller;


import com.upgrad.bookingService.Dto.TransactionInfoDto;
import com.upgrad.bookingService.entity.Booking;
import com.upgrad.bookingService.entity.Transaction;
import com.upgrad.bookingService.exception.BookNotFoundException;
import com.upgrad.bookingService.exception.InvalidCardException;
import com.upgrad.bookingService.Dto.BookingInfoEntity;
import com.upgrad.bookingService.Dto.PaymentDto;
import com.upgrad.bookingService.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/hotel")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping(value = "/booking",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBooking(@RequestBody BookingInfoEntity bookingInfoEntity) {

        Booking newBooking = modelMapper.map(bookingInfoEntity, Booking.class);
        Booking savedBooking = bookingService.acceptBooking(newBooking);

        BookingInfoEntity savedBookingInfoEntity = modelMapper.map(savedBooking, BookingInfoEntity.class);
        return new ResponseEntity(savedBookingInfoEntity, HttpStatus.CREATED);
    }

    @PostMapping(value = "/booking/{bookingId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity paymentReponse(@RequestBody PaymentDto paymentDto, @PathVariable(name = "bookingId") int id) {
        String mode = paymentDto.getPaymentMode();
        if (!(mode.equals("UPI") || mode.equals("CARD"))) {
            throw new InvalidCardException(" message : Invalid mode of payment  , statusCode: 400");
        }
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> userUriMap = new HashMap<>();
        userUriMap.put("id", String.valueOf(id));
        Transaction receiveTrans = restTemplate.getForObject("http://localhost:8083/payment/transaction/book/{id}", Transaction.class, userUriMap);

        TransactionInfoDto recevieTransDto = modelMapper.map(receiveTrans, TransactionInfoDto.class);

        Booking updateBooking = bookingService.getBookingDetails(id);

        updateBooking.setTransactionId(recevieTransDto.getTransactionId());
        BookingInfoEntity book = modelMapper.map(updateBooking, BookingInfoEntity.class);
        String message = "Booking confirmed for user with aadhaar number: "
                + book.getAadharNumber()
                + "    |    "
                + "Here are the booking details:    " + book.toString();
        System.out.println(message);
        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    @GetMapping(value = "/booking/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBooking(@PathVariable(name = "id") int id) {
        Booking getBook = bookingService.getBookingDetails(id);
        BookingInfoEntity newBooking = modelMapper.map(getBook, BookingInfoEntity.class);
        return new ResponseEntity(newBooking, HttpStatus.OK);
    }
}
