package com.jbk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.StudentDao;
import com.jbk.entities.Student;
import com.jbk.entities.User;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public int createStudent(Student student) {
        int status = studentDao.createStudent(student);
        return status;
    }
    
    public Student getStudentById(long id) {
        return studentDao.getStudentById(id);
    }

    public int deleteStudent(long id) {
        return studentDao.deleteStudent(id);
    }
    
    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

	@Override
	public int createMultipleStudent(List<Student> student) {
		int status = studentDao.createMultipleStudent(student);
        return status;
		
	}
	
	@Override
    public int updateStudent(Student updatedStudent) {
        return studentDao.updateStudent(updatedStudent);
    }
    
}

