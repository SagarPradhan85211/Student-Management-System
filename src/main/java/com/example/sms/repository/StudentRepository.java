package com.example.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAll();

    boolean existsByFirstNameAndEmail(String firstName, String email);
    boolean existsByFirstNameAndEmailAndIdNot(String firstName, String email, Long studentId);

}
