package com.upgrad.bookingService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    @Column
    private Date fromDate;
    @Column
    private Date toDate;
    @Column
    private String aadharNumber;
    @Column
    private int numOfRooms;
    @Column
    private String roomNumbers;
    @Column(nullable = false)
    private int roomPrice;
    @ColumnDefault("0")
    @Column
    private int transactionId;
    @Column
    private Date bookedOn;
}
