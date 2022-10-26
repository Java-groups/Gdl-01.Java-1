package com.softserve.exceptions;

import lombok.Data;

@Data
public class UserException extends Exception{
    private String message;

    public UserException(String message){
        super(message);
        this.message = message;
    }
}
