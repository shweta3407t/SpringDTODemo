package com.example.SpringDTODemo.exception;

public class DupticateResourceException  extends  RuntimeException{

    public DupticateResourceException(String message){
        super(message);
    }
}
