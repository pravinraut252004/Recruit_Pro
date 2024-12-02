package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entities.Student;
import com.jbk.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create-student")
    public String createStudent(@RequestBody Student student) {
        int status = studentService.createStudent(student);

        switch (status) {
            case 1:
                return "Student Added Successfully";
            case 2:
                return "Student Already Exists";
            case 3:
                return "Something went Wrong";
            default:
                return "";
        }
    }
    
    
    @PostMapping("/create-Multiplestudent")
    public String createMultipleStudent(@RequestBody List<Student> student) {
        int status = studentService.createMultipleStudent(student);

        switch (status) {
            case 1:
                return "Student Added Successfully";
            case 2:
                return "Student Already Exists";
            case 3:
                return "Something went Wrong";
            default:
                return "";
        }
    }
    
    
    
    
    
    
    @GetMapping("/by-id/{id}")
    public Object getStudentById(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return student;
        } else {
            return "Student not found for ID = " + id;
        }
    }
    
    @GetMapping("/get-all")
    public Object getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        if (!students.isEmpty()) {
            return students;
        } else {
            return "No students found.";
        }
    }
    
  

    @DeleteMapping("/delete-student")
    public String deleteStudent(@RequestParam long id) {
        int status = studentService.deleteStudent(id);

        switch (status) {
            case 1:
                return "Student Deleted Successfully";
            case 2:
                return "Student Not Exists";
            case 3:
                return "Something went Wrong";
            default:
                return "";
        }
    }
    
    @PutMapping("/update-student")
    public String updateStudent(@RequestBody Student updatedStudent) {
        int status = studentService.updateStudent(updatedStudent);

        switch (status) {
            case 1:
                return "Student updated successfully.";
            case 2:
                return "Student not found.";
            default:
                return "Something went wrong.";
        }
    }

    
    
}
