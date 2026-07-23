package com.example.SpringDTODemo.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ValidationExceptionResponseDTO {
    private LocalDateTime timeStamp;
    private  int StatusCode;
    private String error;
    private  String message;
    private  String path;
    private Map<String ,String> fieldsErrors ;


    public ValidationExceptionResponseDTO(LocalDateTime timeStamp, int statusCode, String error, String message, String path, Map<String, String> fieldsErrors) {
        this.timeStamp = timeStamp;
        StatusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldsErrors = fieldsErrors;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFieldsErrors() {
        return fieldsErrors;
    }

    public void setFieldsErrors(Map<String, String> fieldsErrors) {
        this.fieldsErrors = fieldsErrors;
    }
}
