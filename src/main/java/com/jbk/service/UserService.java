package com.jbk.service;

import org.springframework.stereotype.Service;

import com.jbk.dto.LoginRequest;
import com.jbk.entities.User;
@Service
public interface UserService {
	
	public int createUser(User user);

	public User getUserByUsername(String username);
	
	public int deleteUser(String username);

	public User loginUser(LoginRequest loginRequest);

	public int updateUser(User updatedUser);


	



}
