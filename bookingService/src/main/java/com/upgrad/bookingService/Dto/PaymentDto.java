package com.upgrad.bookingService.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private String paymentMode;
    private int bookingId;
    private String upiId;
    private String cardNumber;
}