package com.example.SpringDTODemo.controller;

import com.example.SpringDTODemo.dto.CreateStudentRequestDTO;
import com.example.SpringDTODemo.dto.CreateStudentResponseDTO;
import com.example.SpringDTODemo.dto.UpdateStudentRequestDTO;
import com.example.SpringDTODemo.dto.UpdateStudentResponseDTO;
import com.example.SpringDTODemo.entity.Student;
import com.example.SpringDTODemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/student")
public class StudentController {
    private  StudentService studentService;

    public StudentController(StudentService s){
        this.studentService=s;
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
            @Valid
            @RequestBody CreateStudentRequestDTO student){

        CreateStudentResponseDTO createdStudent=studentService.createStudent(student);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getOneStudent(@RequestParam Long id){
        CreateStudentResponseDTO studentResponse=studentService.getStudent(id);

        return ResponseEntity.ok().body(studentResponse);
    }

    //read All student
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent( ){
        List<Student> studentList=studentService.getAllStudent();

        return ResponseEntity.ok(studentList);
    }

    //update one student
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateOneStudent(
            @RequestParam Long id, @RequestBody UpdateStudentRequestDTO studentResp){

        UpdateStudentResponseDTO newStudent=studentService. update(id , studentResp);

        return ResponseEntity.ok().body(newStudent);
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteOneStudent(@RequestParam Long id){
         studentService.deleteOneStudent(id);

        return ResponseEntity.noContent().build();
    }


    //delete all
    @DeleteMapping
    public  ResponseEntity<String> deleteAllStudent(){
        studentService.deleteAllStudent();
        return ResponseEntity.ok( "All student deleted");
    }


    //soft delete
    @PatchMapping("/softDelete/{id}")
    public  ResponseEntity<String> softDelete(@RequestParam Long id){
         studentService.softDelete(id);

        return ResponseEntity.noContent().build();
    }


    //soft delete all
    @PatchMapping("/softDeleteAll")
    public  ResponseEntity<String> softDeleteAllStudent( ){
        Boolean s=studentService.softDeleteAllStudent();
        return ResponseEntity.ok("soft deleted All student");
    }

}