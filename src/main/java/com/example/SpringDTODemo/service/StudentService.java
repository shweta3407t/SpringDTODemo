package com.example.SpringDTODemo.service;

import com.example.SpringDTODemo.dto.CreateStudentRequestDTO;
import com.example.SpringDTODemo.dto.CreateStudentResponseDTO;
import com.example.SpringDTODemo.dto.UpdateStudentRequestDTO;
import com.example.SpringDTODemo.dto.UpdateStudentResponseDTO;
import com.example.SpringDTODemo.entity.Student;
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
       student.setCreatedAt(LocalDateTime.now());

        student.setUpdatedAt(LocalDateTime.now());


       Student studentResponse=studentRepository.save(student);

       return  mapToDTO(studentResponse);
    }

    public  Student getStudent( Long id){
        Optional<Student> student=studentRepository.findByIdAndDeletedFalse( id);
        if(student.isPresent()){
            return student.get();
        }
        return null;
    }


    public List<Student> getAllStudent(){
        List<Student> studentList=studentRepository.findByDeletedFalse();
        return studentList;
    }


    public UpdateStudentResponseDTO update(  Long id , UpdateStudentRequestDTO student){
        Optional<Student> oldStudent =studentRepository.findByIdAndDeletedFalse(id);

        if(oldStudent.isEmpty()){
            return null;
        }
        Student newStudent=oldStudent.get( );

        newStudent.setDeleted(false);

        newStudent.setName(student.getName());
        newStudent.setAge(student.getAge());
        newStudent.setRollNo(student. getRollNo());
        newStudent.setSubject(student.getSubject());

        newStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent= studentRepository.save(newStudent);

        return mapToUpdatedDTO(savedStudent);
    }

    public Boolean deleteOneStudent(Long id){

        Boolean isDeleted=studentRepository.existsById(id);

        if( ! isDeleted  ) return false;

        studentRepository.deleteById(id);
        return true;

    }

    public  void deleteAllStudent(){
        studentRepository. deleteAll();
    }


    public Boolean softDelete(Long id){

        Optional<Student> existingStudent=studentRepository.findByIdAndDeletedFalse(id) ;

        if(existingStudent.isEmpty()){
            return  false;
        }

        Student studentToSave=existingStudent.get();
        studentToSave.setDeleted(true);

        studentRepository.save(studentToSave);
        return  true;

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
