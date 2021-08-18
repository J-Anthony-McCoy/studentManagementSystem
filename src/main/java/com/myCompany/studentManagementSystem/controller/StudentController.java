package com.myCompany.studentManagementSystem.controller;

import com.myCompany.studentManagementSystem.model.Student;
import com.myCompany.studentManagementSystem.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping(value = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        try{
            Student _student = studentRepo
                    .save(new Student(student.getFirstName()));
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
