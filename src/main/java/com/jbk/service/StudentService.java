package com.jbk.service;

import java.util.List;

import com.jbk.entities.Student;

public interface StudentService {
    public int createStudent(Student student);

	public Student getStudentById(long id);

	public int deleteStudent(long id);
	
	List<Student> getAllStudents();

	public int createMultipleStudent(List<Student> student);

	public int updateStudent(Student updatedStudent);

}
