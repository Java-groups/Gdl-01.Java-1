package com.softserve.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ArticleException extends Exception{
    private String message;

    public ArticleException(String message){
        super(message);
        this.message =message;
    }
}
