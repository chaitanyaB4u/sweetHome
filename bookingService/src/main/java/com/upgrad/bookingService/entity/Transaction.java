package com.upgrad.bookingService.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    @Column
    private String paymentMode;
    @Column(nullable = false)
    private int bookingId;
    @Column
    private String upiId;
    @Column
    private String cardNumber;
}
