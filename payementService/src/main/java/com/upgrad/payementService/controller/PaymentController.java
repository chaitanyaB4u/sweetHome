package com.upgrad.payementService.controller;

import com.upgrad.payementService.entity.Transaction;
import com.upgrad.payementService.exception.BookNotFoundException;
import com.upgrad.payementService.model.TransactionDetailsEntity;
import com.upgrad.payementService.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTransactionId(@RequestBody TransactionDetailsEntity transactionDetailsEntity) {
        Transaction saveTransaction = modelMapper.map(transactionDetailsEntity, Transaction.class);
        Transaction newTransaction = transactionService.acceptTransaction(saveTransaction);
        TransactionDetailsEntity transactionDetailsEntity1 = modelMapper.map(newTransaction, TransactionDetailsEntity.class);
        return new ResponseEntity(transactionDetailsEntity1.getTransactionId(), HttpStatus.CREATED);
    }
    @GetMapping("/transaction/{transactionId}")
    public  ResponseEntity getTransactionDetails(@PathVariable(name="transactionId") int id) {
        Transaction savedTransaction= transactionService.getTransaction(id);
        TransactionDetailsEntity transactionDetailsEntity= modelMapper.map(savedTransaction,TransactionDetailsEntity.class);
        return new ResponseEntity<>(transactionDetailsEntity,HttpStatus.OK);
    }
    @GetMapping("/transaction/book/{id}")
    public  ResponseEntity getTransactionBookDetails(@PathVariable(name="id") int id) {
        Transaction savedTransaction= transactionService.getTranscationByBookIdDetail(id);
        TransactionDetailsEntity transactionDetailsEntity= modelMapper.map(savedTransaction,TransactionDetailsEntity.class);
        return new ResponseEntity<>(transactionDetailsEntity,HttpStatus.OK);
    }
}
