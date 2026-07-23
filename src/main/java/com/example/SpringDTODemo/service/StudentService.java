package com.example.SpringDTODemo.service;

import com.example.SpringDTODemo.dto.CreateRequestDTO;
import com.example.SpringDTODemo.dto.CreateResponseDTO;
import com.example.SpringDTODemo.dto.UpdateRequestDTO;
import com.example.SpringDTODemo.dto.UpdateResponseDTO;
import com.example.SpringDTODemo.entity.Student;
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
       Student student=mapTOEntity(studentRequest);

       Student studentResponse=studentRepository.save(student);

       return mapToDTO(studentResponse);
    }


    //read
    public CreateResponseDTO getOneStudent(Long id){
        Student student=studentRepository.findByIdAndDeletedFalse(id);

         return  mapToDTO(student);
    }
    public List<Student> getAllStudent( ){
         List<Student> list=studentRepository. findAll();

        return  list;
    }


    //update
    public  UpdateResponseDTO updateStudent(Long id  ,UpdateRequestDTO studentRequest){
        Student oldStudent=studentRepository.findByIdAndDeletedFalse(id);

        oldStudent.setName(studentRequest.getName());
        oldStudent.setAge(studentRequest.getAge());
        oldStudent.setRollNo(studentRequest.getRollNo());
        oldStudent.setSubject(studentRequest.getName());

        Student updatedStudent=studentRepository.save(oldStudent);

        return  mapToUpdateDTO(updatedStudent);
    }



    //delete
    public Student deleteOneStudent(Long id){

        Student student=studentRepository.
                findById(id).
                orElseThrow(() -> new RuntimeException("Student with id "+ id + "not exist."));

        studentRepository.delete(student);

          return student;
    }

    public void deleteAllStudent(){
        studentRepository.deleteAll();
    }



    //soft deletde
    public  Student softDeleteOneStudent(Long id){
        Student student=studentRepository.findByIdAndDeletedFalse(id);
        student.setDeleted(true);

        studentRepository.save(student);

        return student;
    }


    public  void softDeleteAllStudent(){
        List<Student> list =studentRepository.findByDeletedFalse();
        for(Student s : list){
            s.setDeleted(true);
        }
    }
















    //mapping
    public  Student mapTOEntity(CreateRequestDTO studentRequest){
        Student student=new Student();

        student.setId(studentRequest.getId());
        student.setName (studentRequest.getName() );
        student.setAge(studentRequest. getAge());
        student.setRollNo(studentRequest.getRollNo());
        student.setSubject(studentRequest.getSubject());
        student.setEmail(studentRequest.getEmail());

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

        studentResponse.setCreatedAt(student.getCreatedAt());
        studentResponse.setUpdatedAt(student.getUpdatedAt());

        studentResponse.setMessage("Student created successfully");


        return  studentResponse;
    }











    public  Student mapTOUpdateEntity(UpdateRequestDTO studentRequest){
        Student student=new Student();

         student.setName (studentRequest.getName() );
        student.setAge(studentRequest. getAge());
        student.setRollNo(studentRequest.getRollNo());
        student.setSubject(studentRequest.getSubject());

        student.setDeleted(false);

        studentRepository.save(student);

        return student;
    }

    public UpdateResponseDTO mapToUpdateDTO(Student student){
        UpdateResponseDTO studentResponse=new UpdateResponseDTO();

        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setSubject(student.getSubject());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setRollNo(student.getRollNo());

        studentResponse.setDeleted( student.getDeleted());

        studentResponse.setUpdatedAt(LocalDateTime.now());
        studentResponse.setMessage("Student created successfully");

        return  studentResponse;
    }


}
