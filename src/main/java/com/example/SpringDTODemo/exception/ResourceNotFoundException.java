package com.example.SpringDTODemo.exception;

public class ResourceNotFoundException extends  RuntimeException {
     public ResourceNotFoundException(String message){
        super(message);
    }

}
