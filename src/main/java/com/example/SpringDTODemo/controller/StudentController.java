package com.example.SpringDTODemo.controller;


import com.example.SpringDTODemo.dto.CreateRequestDTO;
import com.example.SpringDTODemo.dto.CreateResponseDTO;
import com.example.SpringDTODemo.dto.UpdateRequestDTO;
import com.example.SpringDTODemo.dto.UpdateResponseDTO;
import com.example.SpringDTODemo.entity.Student;
import com.example.SpringDTODemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    public  StudentController (StudentService s){
        this.studentService=s;
    }

    //create
    @PostMapping
    public ResponseEntity<CreateResponseDTO> createStudent(@Valid  @RequestBody CreateRequestDTO studentRequest){
        CreateResponseDTO studentResponse=studentService.createStudent(studentRequest);

        return  ResponseEntity.ok().body(studentResponse);
    }




    //read
    @GetMapping("/{id}")
    public  ResponseEntity<CreateResponseDTO> getOneStudent(@PathVariable Long id){
        CreateResponseDTO studentResponse=studentService.getOneStudent(id);
        return  ResponseEntity.ok().body(studentResponse);
    }

    @GetMapping
    public  ResponseEntity<List<Student>> getAllStudent(){
        List<Student> studentResponse=studentService.getAllStudent( );

        return  ResponseEntity.ok().body(studentResponse);
    }







    //update
    @PutMapping("/{id}")
    public  ResponseEntity<UpdateResponseDTO> updateStudent(@PathVariable Long id , @RequestBody UpdateRequestDTO updateStudent){
        UpdateResponseDTO  student=studentService.updateStudent(id ,updateStudent );

        return  ResponseEntity.ok( ).body(student);
    }







    //delete
    @DeleteMapping("/{id}")
    public  ResponseEntity< String> deleteOneStudent(@PathVariable Long id){
        Student student=studentService.deleteOneStudent(id);

        return  ResponseEntity.ok( "Student with id " + student.getId() + "deleted permanently") ;
    }

    @DeleteMapping
    public  ResponseEntity< String> deleteAllStudent( ){
         studentService.deleteAllStudent( );

        return  ResponseEntity.ok( " All student deleted permanently") ;
    }





    //soft delete
    @PatchMapping("/softDelete/id")
    public  ResponseEntity< String> softDeleteOneStudent(@PathVariable Long id){
        Student student=studentService.softDeleteOneStudent(id);

        return  ResponseEntity.ok(  "Soft deleted student with id" + student.getId()) ;
    }

    @PatchMapping("/softDelete")
    public  ResponseEntity< String> softDeleteAllStudent(){
        studentService.softDeleteAllStudent( );

        return  ResponseEntity.ok(  "Soft deleted all student  ") ;
    }


}
