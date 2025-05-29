package com.example.CarSalesAgency.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateTestDriveException extends RuntimeException {
    public DuplicateTestDriveException(String message) {
        super(message);
    }
}