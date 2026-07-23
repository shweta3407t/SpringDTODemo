package com.example.SpringDTODemo.service;

import com.example.SpringDTODemo.dto.CreateRequestDTO;
import com.example.SpringDTODemo.dto.CreateResponseDTO;
import com.example.SpringDTODemo.dto.UpdateRequestDTO;
import com.example.SpringDTODemo.dto.UpdateResponseDTO;
import com.example.SpringDTODemo.entity.Student;
import com.example.SpringDTODemo.exception.DuplicateResourceException;
import com.example.SpringDTODemo.exception.ResourceNotFoundException;
import com.example.SpringDTODemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService (StudentRepository s){
        this.studentRepository=s;
    }

    //create
    public CreateResponseDTO createStudent(CreateRequestDTO studentRequest){
        String email =studentRequest.getEmail();

       if(emailExist(email)){
           throw  new DuplicateResourceException("Student with email id " +email + " already exist.");
       }

        Student student=mapTOEntity(studentRequest);
       Student studentResponse=studentRepository.save(student);

       return mapToDTO(studentResponse);
    }


    //read
    public CreateResponseDTO getOneStudent(Long id){
        Student student=studentRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id "+ id + "not exist."));


        return  mapToDTO(student);
    }

    public List<Student> getAllStudent( ){
         List<Student> list=studentRepository. findByDeletedFalse();

        return  list;
    }


    //update
    public  UpdateResponseDTO updateStudent(Long id  ,UpdateRequestDTO studentRequest){
        Student existingStudent=studentRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id "+ id + "not exist."));


        existingStudent.setName(studentRequest.getName());
        existingStudent.setAge(studentRequest.getAge());
        existingStudent.setRollNo(studentRequest.getRollNo());
        existingStudent.setSubject(studentRequest.getName());

        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent=studentRepository.save(existingStudent);

        return  mapToUpdateDTO(updatedStudent);
    }



    //delete
    public Student deleteOneStudent(Long id){

        Student student=studentRepository.
                findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id "+ id + "not exist."));

        studentRepository.delete(student);

          return student;
    }

    public void deleteAllStudent(){
        studentRepository.deleteAll();
    }



    //soft deletde
    public  Student softDeleteOneStudent(Long id){
        Student student=studentRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Student with id "+ id + "not exist."));

        student.setDeleted(true);

        studentRepository.save(student);

        return student;
    }


    public  void softDeleteAllStudent(){
        List<Student> list =studentRepository
                .findByDeletedFalse();

        for(Student s : list){
            s.setDeleted(true);
            studentRepository.save(s);
        }

    }



    //remove soft delete

    public  Student removeSoftDeleteOneStudent(Long id){
        Student student=studentRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Student with id "+ id + " not exist."));

        student.setDeleted(false);

        studentRepository.save(student);

        return student;
    }

    public  void removeSoftDeleteAllStudent(){
        List<Student> list =studentRepository
                .findByDeletedFalse();

        for(Student s : list){
            s.setDeleted(false);
            studentRepository.save(s);
        }

    }

















    private  boolean emailExist(String email){
        return  studentRepository.existsByEmail( email);
     }


    //mapping
    public  Student mapTOEntity(CreateRequestDTO studentRequest){
        Student student=new Student();

        student.setName (studentRequest.getName() );
        student.setAge(studentRequest. getAge());
        student.setRollNo(studentRequest.getRollNo());
        student.setSubject(studentRequest.getSubject());
        student.setEmail(studentRequest.getEmail());

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);

        studentRepository.save(student);

        return student;
    }

    public  CreateResponseDTO mapToDTO(Student student){
        CreateResponseDTO studentResponse=new CreateResponseDTO();

         studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setSubject(student.getSubject());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setRollNo(student.getRollNo());

        studentResponse.setDeleted( student.getDeleted());

        studentResponse.setCreatedAt(LocalDateTime.now());
        studentResponse.setUpdatedAt(LocalDateTime.now());

        studentResponse.setMessage("Student created successfully");


        return  studentResponse;
    }











    ///no use
//    public  Student mapTOUpdateEntity(UpdateRequestDTO studentRequest){
//        Student student=new Student();
//
//        student.setName(studentRequest.getName());
//        student.setAge(studentRequest.getAge());
//        student.setSubject(studentRequest.getSubject());
//        student.setRollNo(studentRequest.getRollNo());
//
//        studentRepository.save(student);
//
//        return student;
//    }

    public UpdateResponseDTO mapToUpdateDTO(Student student){
        UpdateResponseDTO studentResponse=new UpdateResponseDTO();


        studentResponse.setName (student.getName() );
        studentResponse.setAge(student. getAge());
        studentResponse.setRollNo(student.getRollNo());
        studentResponse.setSubject(student.getSubject());

        studentResponse.setDeleted(false);

        studentResponse.setEmail(student.getEmail());

        studentResponse.setDeleted( student.getDeleted());

        studentResponse.setUpdatedAt(LocalDateTime.now());

        studentResponse.setMessage("Student updated successfully");

        return  studentResponse;
    }


}
