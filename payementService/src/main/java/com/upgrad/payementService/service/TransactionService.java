package com.upgrad.payementService.service;

import com.upgrad.payementService.entity.Transaction;

public interface TransactionService {
    Transaction acceptTransaction(Transaction transaction);
    Transaction getTransaction(int id);
    Transaction getTranscationByBookIdDetail(int id);

}
