package com.example.SpringDTODemo.service;

import com.example.SpringDTODemo.dto.CreateStudentRequestDTO;
import com.example.SpringDTODemo.dto.CreateStudentResponseDTO;
import com.example.SpringDTODemo.dto.UpdateStudentRequestDTO;
import com.example.SpringDTODemo.dto.UpdateStudentResponseDTO;
import com.example.SpringDTODemo.entity.Student;
import com.example.SpringDTODemo.exception.DupticateResourceException;
import com.example.SpringDTODemo.exception.ResourceNotFoundException;
import com.example.SpringDTODemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository r){
        this.studentRepository=r;
    }



    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentResq){
       Student student= mapToEntity(studentResq);


       if(emailExist(student) ){
           throw new DupticateResourceException("Student with email "+student.getEmail() + " already exist");
       }

       Student studentResponse=studentRepository.save(student);

       return  mapToDTO(studentResponse);
    }

    public  CreateStudentResponseDTO getStudent( Long id){
         Student studentResp=studentRepository
                 .findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id+" not found"));

         return mapToDTO(studentResp);
    }


    public List<Student> getAllStudent(){
        List<Student> studentList=studentRepository.findByDeletedFalse();
        return studentList;
    }


    public UpdateStudentResponseDTO update(  Long id , UpdateStudentRequestDTO student){
         Student oldStudent=studentRepository
                 .findByIdAndDeletedFalse(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id+  " not found."));


        oldStudent.setDeleted(false);

        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setRollNo(student. getRollNo());
        oldStudent.setSubject(student.getSubject());

        oldStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent= studentRepository.save(oldStudent);

        return mapToUpdatedDTO(savedStudent);
    }

    public void deleteOneStudent(Long id){

        Student studentToBeDeleted=studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id " + id+  " not found."));

        studentRepository.delete(studentToBeDeleted);

    }

    private  boolean emailExist(Student student){
        return  studentRepository.existsByEmail(student.getEmail());


    }

    public  void deleteAllStudent(){
        studentRepository. deleteAll();
    }


    public void softDelete(Long id){

         Student studentToBeDeleted=studentRepository.findByIdAndDeletedFalse(id)
                 .orElseThrow(() ->
                         new ResourceNotFoundException("Student with id " + id+  " not found."));

         studentToBeDeleted.setDeleted(true);

        studentRepository.save(studentToBeDeleted);
    }


    public   Boolean softDeleteAllStudent(){
        List<Student> s=studentRepository.findByDeletedFalse();
        for (Student student : s){
            student.setDeleted(true);
            studentRepository.save(student);
        }

        return  true;
    }

    private  Student mapToEntity(CreateStudentRequestDTO studentRequest){
        Student student=new Student();
        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setSubject(studentRequest.getSubject());
        student.setRollNo(studentRequest. getRollNo());

        student.setCreatedAt(LocalDateTime.now());

        student.setUpdatedAt(LocalDateTime.now());


        //builder pattern

        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDTO mapToDTO(Student student){

        CreateStudentResponseDTO responseDTO=new CreateStudentResponseDTO();

        responseDTO.setId(student.getId());

        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setSubject(student.getSubject());

        responseDTO.setMessage("Student created successfully");

        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());

        return  responseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdatedDTO(Student student){
        UpdateStudentResponseDTO responseDTO=new UpdateStudentResponseDTO();

        responseDTO.setId(student.getId());

        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setSubject(student.getSubject());

        responseDTO.setMessage("Student updated successfully");

         responseDTO.setUpdatedAt(student.getUpdatedAt());

        return  responseDTO;
    }


    private UpdateStudentResponseDTO mapToUpdateDTO(Student student){
        UpdateStudentResponseDTO responseDTO=new UpdateStudentResponseDTO();

        responseDTO.setId(student.getId());

        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setSubject(student.getSubject());

        responseDTO.setMessage("Student updated successfully");

         responseDTO.setUpdatedAt(student.getUpdatedAt());

        return  responseDTO;
    }

}
