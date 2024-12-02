package com.jbk.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jbk.dto.LoginRequest;
import com.jbk.entities.Student;
import com.jbk.entities.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int createUser(User user) {
		
		try {
			Session session = sessionFactory.openSession();
			
			User dbUser = session.get(User.class, user.getUsername());
			
			if (dbUser==null) {
				session.save(user);
				session.beginTransaction().commit();
				return 1;
			}else {
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
			
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		try {
			Session session = sessionFactory.openSession();
			user = session.get(User.class, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return user;
	}

	@Override
	public int deleteUser(String username) {
		
		try {
			Session session = sessionFactory.openSession();
			
			User dbUser = session.get(User.class, username);
			
			if (dbUser!=null) {
				session.delete(dbUser);
				session.beginTransaction().commit();
				return 1;
			}else {
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
	}
	
	
	public User loginUser(LoginRequest loginRequest) {
		try {
            Session session = sessionFactory.openSession();
			
			User dbUser = session.get(User.class,loginRequest.getUsername());
			
			if(dbUser != null) {
				if(dbUser.getPassword().equals(loginRequest.getPassword())) {
					return dbUser;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int updateUser(User user) {
	    Session session = null;
	    Transaction transaction = null;

	    try {
	        session = sessionFactory.openSession();
	        transaction = session.beginTransaction();

	        User existingUser = session.get(User.class, user.getUsername());
	        if (existingUser != null) {
	           
	            existingUser.setPassword(user.getPassword());
	            existingUser.setQuestion(user.getQuestion());
	            existingUser.setAnswer(user.getAnswer());
	            existingUser.setRole(user.getRole());

	           
	            transaction.commit();
	            return 1; 
	        } else {
	            return 2; 
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return 3; 
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}

	
	

}
