package com.example.SpringDTODemo.exception;

public class DuplicateResourceException  extends RuntimeException{
    public DuplicateResourceException(String message){
        super(message);
    }
}
