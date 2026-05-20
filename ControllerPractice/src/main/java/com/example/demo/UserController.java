package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public String getUser() {
	    System.out.println("Controller called");

		return "User found from Controller";
	}
    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {

        if (id != 1) {
            throw new UserNotFoundException("User not found");
        }

        return "User found";
    }
	
	@GetMapping("/users")
	public String getUserFromUserService() {
		return userService.fetchUser();
	}
	
	@GetMapping("/userdb")

	public String fetchUserFromDb() {
		return userService.fetchUsers();
		
	}
}
