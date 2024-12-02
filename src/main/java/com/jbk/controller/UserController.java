package com.jbk.controller;

import javax.validation.Valid;

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
import com.jbk.entities.User;
import com.jbk.exceptions.ResourceNotFoundException;
import com.jbk.service.StudentService;
import com.jbk.service.UserService;
import com.jbk.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/create-user")
	public String createUser(@RequestBody @Valid User user) {
		
		int status = userService.createUser(user);
		
		switch (status) {
		case 1: {
			
			return "User Added Successfully";
		}
        case 2: {
			
			return "User Already Exists";
		}
        
        case 3: {
			
			return "Something went Wrong";
		}
		default:
			return "";
		
	}
}
	
	@GetMapping("by-username/{username}")
	public Object getUserByUsername(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		
		if (user != null) {
			return user;
			
		}else {
//			return "User not found for username = "+username; 
			throw new ResourceNotFoundException("User not found for username = " + username);
	} 
}
	
	@DeleteMapping("/delete-user")
	public String deleteUser(@RequestParam String username) {
        int status = userService.deleteUser(username);
		
		switch (status) {
		case 1: {
			
			return "User Delete Successfully";
		}
        case 2: {
			
			return "User Not Exists";
		}
        
        case 3: {
			
			return "Something went Wrong";
		}
		default:
			return "";
	}
	
	}
	
	@PutMapping("/update-user")
    public String updateUser(@RequestBody User updatedUser) {
        int status = userService.updateUser(updatedUser);

        switch (status) {
            case 1:
                return "User updated successfully.";
            case 2:
                return "User not found.";
            default:
                return "Something went wrong.";
        }
    }
	
	
}
