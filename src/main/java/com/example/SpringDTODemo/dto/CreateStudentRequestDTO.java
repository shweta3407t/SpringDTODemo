package com.example.SpringDTODemo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {

    @NotBlank(message =  "Name cannot be null/empty or blank")
    @Size(min = 2 ,max = 50 , message = "Student name must be within 2 and 50 character long")
     private String name;

    @NotNull(message =  "Roll No. required")
    private Integer rollNo;

    @Min(value = 18,message = "Age must be abow 18 years old")
    @Max(40)
    private  Integer age;

    @NotBlank(message =  "Subject cannot be null/empty or blank")
    private String subject;

    @NotBlank(message =  "Email cannot be null/empty or blank")
    @Email(message = "Email must be valid")
    private String  email;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
