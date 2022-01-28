package com.upgrad.payementService.feign;

import com.upgrad.payementService.model.TransactionDetailsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("API-GATEWAY")
public interface PaymentServiceClient {
    @PostMapping(value = "/payment/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTransactionId(@RequestBody TransactionDetailsEntity transactionDetailsEntity);
    @GetMapping("/payment/transaction/{transactionId}")
    public  ResponseEntity getTransactionDetails(@PathVariable(name="transactionId") int id) ;
}
