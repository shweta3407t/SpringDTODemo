package com.example.SpringDTODemo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class UpdateRequestDTO {

    @NotBlank(message = "name should not be null/empty or blank")
    private  String name;

    @NotNull(message = "age should not be null/empty or blank")
    @Min(10) @Max(value = 50 ,message = "Age should be between 10 -50 years old")
    private Integer age;

    @NotBlank(message = "subject should not be null/empty or blank")
    private String subject;

    @NotNull(message = "roll number should not be null/empty or blank")
    private  Integer rollNo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }
}
