package com.example.SpringDTODemo.dto;

import jakarta.validation.constraints.*;


public class CreateRequestDTO {


    @NotBlank(message = "name should not be null/empty or blank")
    @Size(min =2 , max=50 , message = "Student name must be withine 2 - 50 character")
    private String name;

    @NotNull(message = "age should not be null/empty or blank")
    @Min(10) @Max(value = 50 ,message = "Age should be between 10 -50 years old")
    private Integer age;

    @NotBlank(message = "subject should not be null/empty or blank")
    private String subject;

    @NotNull(message = "rollNumber should not be null/empty or blank")
    private Integer rollNo;

    @NotBlank(message = "name should not be null/empty or blank")
    @Email
    private String email;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
