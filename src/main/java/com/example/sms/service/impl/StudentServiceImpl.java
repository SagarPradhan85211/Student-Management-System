package com.example.sms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.sms.entity.Student;
import com.example.sms.repository.StudentRepository;
import com.example.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            logger.info("Action: GET ALL STUDENTS");
            return studentRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all students: {}", e);
            throw e; // Re-throw the exception if needed
        }
    }

    @Override
    public Student saveStudent(Student student) {
        try {
            logger.info("Action: ADD", student.getFirstName());
            if(studentRepository.existsByFirstNameAndEmail(student.getFirstName(), student.getEmail())){
            logger.warn("A student with same name and email already exists: {}", student.getFirstName());
            throw new IllegalArgumentException("A student with same name and email already exists");
            }
            Student saveStudent = studentRepository.save(student);
            logger.info("Successfully save student: {}", saveStudent.getFirstName());

            return saveStudent;
        } catch (Exception e) {
            logger.error("Error adding student: {}", e);
            throw e; // Re-throw the exception if needed
        }
    }

    @Override
    public Student getStudentById(Long studentId) {

        try {
            logger.info("Action: GET STUDENT BY ID, Student ID: {}", studentId);
            return studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    logger.warn("Student not found with ID: {}",studentId);
                    return new IllegalArgumentException("Student not found with id:"+ studentId);
                });
        } catch (Exception e) {
            logger.error("Error fetching student with ID {}: {}", studentId, e);
            throw e; // Re-throw the exception if needed
        }
    }

    @Override
    public Student updateStudent(Student student) {
       
        try {
            logger.info("Action: UPDATE");
            if(studentRepository.existsByFirstNameAndEmailAndIdNot(student.getFirstName(), student.getEmail(), student.getId())){
            logger.warn("A student with same name and email already exists: {}", student.getFirstName());
            throw new IllegalArgumentException("A student with same name and email already exists");
        } 
        Student updateStudent = studentRepository.save(student);
        logger.info("Successfully updated student: {}", updateStudent.getFirstName());
        return updateStudent;
    }
        catch (Exception e) {
            logger.error("Error updating student: {}", e.getMessage());
            throw e; // Re-throw the exception if needed
        }
    }

    @Override
    public void deleteStudent(Long studentId) {
    
        try {
            logger.info("Action: DELETE");
            studentRepository.deleteById(studentId);
        } catch (Exception e) {
            logger.error("Error deleting student with ID {}: {}", studentId, e.getMessage());
            throw e; // Re-throw the exception if needed
        }
    }

    
}
