package com.example.SpringDTODemo.service;

import com.example.SpringDTODemo.entity.Student;
import com.example.SpringDTODemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository r){
        this.studentRepository=r;
    }

    public  Student createStudent(Student s){
        s.setDeleted(false);

        Student studentResponse=studentRepository.save(s);

        return studentResponse;
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


    public Student update(  Long id , Student student){
        Optional<Student> oldStudent =studentRepository.findByIdAndDeletedFalse(id);

        if(oldStudent.isEmpty()){
            return null;
        }
        Student newStudent=oldStudent.get( );

        newStudent.setDeleted(false);

        newStudent.setName(student.getName());
        newStudent.setAge(student.getAge());
        newStudent.setRollNo(student. getRollNo());
        newStudent.setEmail(student.getEmail());
        newStudent.setSubject(student.getSubject());

        return studentRepository.save(newStudent);
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

}
