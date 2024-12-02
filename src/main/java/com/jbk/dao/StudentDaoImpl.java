package com.jbk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.entities.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public int createStudent(Student student) {
        try {
            Session session = sessionFactory.openSession();

            Student dbStudent = session.get(Student.class, student.getId());

            if (dbStudent == null) {
                session.save(student);
                session.beginTransaction().commit();
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }
    
    public Student getStudentById(long id) {
        Student student = null;
        try {
            Session session = sessionFactory.openSession();
            student = session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public int deleteStudent(long id) {
        try {
            Session session = sessionFactory.openSession();
            Student dbStudent = session.get(Student.class, id);

            if (dbStudent != null) {
                session.beginTransaction();
                session.delete(dbStudent);
                session.getTransaction().commit();
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }
    
    @Override
    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student";
            return session.createQuery(hql, Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	
//		@Override
//	    public int createMultipleStudent(List<Student> students) {
//	        Transaction transaction = null;
//
//	        try (Session session = sessionFactory.openSession()) {
//	            transaction=session.beginTransaction();
//
//	            for (Student student : students) {
//	            	
//	            	Student dbStudent = session.get(Student.class, student.getId());
//	                
//	                // Optionally, flush and clear session to avoid memory issues in large data
//	                if (dbStudent  == null) { 
//	                	session.save(student);
//	                }else {
//	                	System.out.println("Student with id"+student.getId()+"already exist");
//	                }
//	            }
//
//	            transaction.commit();
//	            return 1;
//	        } catch (Exception e) {
//	        	
//	        	if(transaction != null) {
//	        		transaction.rollback();
//	        	}
//	            e.printStackTrace();
//	            return 3;
//	        }
//	    }
//	
//	
    
    @Override
    public int createMultipleStudent(List<Student> students) {
        try (Session session = sessionFactory.openSession()) {
            // Open a transaction
            session.beginTransaction();
            
            // Save all students in one go
            for (Student student : students) {
                session.save(student);  // Save each student
            }
            
            // Commit the transaction
            session.getTransaction().commit();
            
            return students.size();  // Return the number of students created
        } catch (Exception e) {
            e.printStackTrace();
            return 0;  // Return 0 if an error occurs
        }
    }
    
    @Override
    public int updateStudent(Student student) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Check if the student exists
            Student existingStudent = session.get(Student.class, student.getId());
            if (existingStudent != null) {
                // Update the student's details
                existingStudent.setFullName(student.getFullName());
                existingStudent.setEmail(student.getEmail());
                existingStudent.setPassword(student.getPassword());
                existingStudent.setContactNumber(student.getContactNumber());
                existingStudent.setDepartment(student.getDepartment());
                existingStudent.setGraduationYear(student.getGraduationYear());

                // Commit the transaction
                transaction.commit();
                return 1; // Successfully updated
            } else {
                return 2; // Student not found
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 3; // Something went wrong
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
}
