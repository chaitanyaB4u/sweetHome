package com.upgrad.payementService.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private int statusCode;
}
