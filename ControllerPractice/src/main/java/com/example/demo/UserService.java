package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
    public String fetchUser(){
        System.out.println("Service called");

        return "User found from service";
    }
    
    public String fetchUsers(){
        return userRepository.getUserFromDB();
    }

}