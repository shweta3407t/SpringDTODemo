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

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
            @Valid
            @RequestBody CreateStudentRequestDTO student){

        CreateStudentResponseDTO createdStudent=studentService.createStudent(student);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    //read one student
    @GetMapping("/get/{id}") //parameter value ----> 2 type
    public ResponseEntity<Student> getOneStudent(@PathVariable Long id){//1.@Pathvariable     2.@MatrixVariable-->get ? d=1& name=raj
        Student studentResponse=studentService.getStudent(id);

        if(studentResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentResponse);
    }

    //read All student
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent( ){
        List<Student> studentList=studentService.getAllStudent();

        if(studentList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentList);
    }

    //update one student
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateOneStudent(
            @PathVariable Long id, @RequestBody UpdateStudentRequestDTO studentResp){

        UpdateStudentResponseDTO newStudent=studentService. update(id , studentResp);

        if(newStudent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(newStudent);
    }

    //delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  deleteOneStudent(@PathVariable Long id){
        Boolean isDelete=studentService.deleteOneStudent(id);

        if((! isDelete)){
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }


    //delete all
    @DeleteMapping("/deleteAll")
    public  ResponseEntity<String> deleteAllStudent(){
        studentService.deleteAllStudent();
        return ResponseEntity.ok( "All student deleted");
    }


    //soft delete
    @DeleteMapping("/softDelete/{id}")
    public  ResponseEntity<String> softDelete(@PathVariable Long id){
        Boolean student=studentService.softDelete(id);

        if((! student)){
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("softly record deleted");
    }


    //soft delete all
    @PatchMapping("/softDeleteAll")
    public  ResponseEntity<String> softDeleteAllStudent( ){
        Boolean s=studentService.softDeleteAllStudent();
        return ResponseEntity.ok("soft deleted All student");
    }

}