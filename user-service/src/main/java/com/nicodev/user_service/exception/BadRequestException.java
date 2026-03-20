package com.nicodev.user_service.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }

}
