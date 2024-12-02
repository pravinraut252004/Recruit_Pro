package com.jbk.dao;

import java.util.List;

import com.jbk.entities.Student;

public interface StudentDao {

	public int createStudent(Student student);

	public Student getStudentById(long id);

	public int deleteStudent(long id);
	
	List<Student> getAllStudents();

	public int createMultipleStudent(List<Student> student);

	 int updateStudent(Student student);

}
