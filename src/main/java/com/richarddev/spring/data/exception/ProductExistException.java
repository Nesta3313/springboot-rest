package com.richarddev.spring.data.exception;

public class ProductExistException extends RuntimeException{
    public ProductExistException(String message){
        super(message);
    }
}
