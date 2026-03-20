package com.nicodev.product_service.exception;

public class BadRequestException extends RuntimeException{
// name, brand, unit_price

    public BadRequestException(String message) {
        super(message);  // Name depend of the required file.
    }


}
