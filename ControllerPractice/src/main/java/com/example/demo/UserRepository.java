package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public String getUserFromDB(){
    	System.out.println("Repository called.");
        return "User from database";
    }
}