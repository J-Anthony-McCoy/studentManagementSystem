package com.myCompany.studentManagementSystem.controller;

//import com.myCompany.studentManagementSystem.model.Student;
import com.myCompany.studentManagementSystem.model.Student;
import com.myCompany.studentManagementSystem.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping(value = "/")
    public String printTest() {
        return "This is the home page";
    }

    @GetMapping(value = "/students")
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveStudent(@RequestBody Student student){
        studentRepo.save(student);
        return "Student successfully saved!";
    }

    @PutMapping(value = "/update/{id}")
    public String updateStudent (@PathVariable Long id, @RequestBody Student student){
        Student updatedStudent = studentRepo.findById(id).get();
        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setGrade(student.getGrade());
        studentRepo.save(updatedStudent);
        return "Student successfully updated!";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteStudent (@PathVariable Long id){
        Student deleteStudent = studentRepo.findById(id).get();
        studentRepo.delete(deleteStudent);
        return "Deleted student with database id " + id;
    }
}

// LOOK FOR JSON CREATOR TO MAKE A LONG LIST OF STUDENTS AND SAVE SQL FILE FOR FUTURE BUILDS


//    @PostMapping(value = "/students")
//    public ResponseEntity<Student> createStudent(@RequestBody Student student){
//        try{
//            Student _student = studentRepo
//                    .save(new Student(student.getFirstName()));
//            return new ResponseEntity<>(_student, HttpStatus.CREATED);
//        } catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
