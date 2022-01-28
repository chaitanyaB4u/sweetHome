package com.upgrad.bookingService.feign;

import com.upgrad.bookingService.Dto.BookingInfoEntity;
import com.upgrad.bookingService.Dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("API-GATEWAY")
public interface BookingSeviceClient {
    @PostMapping(value = "/hotel/booking",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    BookingInfoEntity redirectMovie(@RequestBody BookingInfoEntity bookingInfoEntity);

    @PostMapping(value = "/booking/{bookingId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity paymentReponse(@RequestBody PaymentDto paymentDto, @PathVariable(name = "bookingId") int id);
}
