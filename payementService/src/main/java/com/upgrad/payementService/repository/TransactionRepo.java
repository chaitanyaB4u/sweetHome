package com.upgrad.payementService.repository;

import com.upgrad.payementService.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    @Query("SELECT u FROM Transaction u WHERE u.bookingId = :id ")
Transaction getTransactionByBookingId(@Param("id") int id);
}
