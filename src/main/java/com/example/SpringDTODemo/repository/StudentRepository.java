package com.example.SpringDTODemo.repository;


import com.example.SpringDTODemo.dto.CreateResponseDTO;
import com.example.SpringDTODemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  StudentRepository  extends JpaRepository<Student , Long> {

    Optional<Student> findByIdAndDeletedFalse(Long id);

    List<Student>  findByDeletedFalse();

    boolean existsByEmail(String email);

}
