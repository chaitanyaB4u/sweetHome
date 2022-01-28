package com.upgrad.payementService.service;

import com.upgrad.payementService.entity.Transaction;
import com.upgrad.payementService.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepo transactionRepo;


    @Override
    public Transaction acceptTransaction(Transaction transaction) {
        Transaction saveTransaction = new Transaction();

        saveTransaction.setBookingId(transaction.getBookingId());
        saveTransaction.setCardNumber(transaction.getCardNumber());
        saveTransaction.setPaymentMode(transaction.getPaymentMode());
        saveTransaction.setUpiId(transaction.getUpiId());
        //  need to hard code booking serve to print on console


        return transactionRepo.save(saveTransaction);
    }

    @Override
    public Transaction getTransaction(int id) {
        return transactionRepo.findById(id).get();
    }

    @Override
    public Transaction getTranscationByBookIdDetail(int id) {

        return transactionRepo.getTransactionByBookingId(id);
    }


}
