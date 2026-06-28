package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
    	if (roleRepository.count() == 0) {
			Role adminRole = new Role();
			adminRole.setRoleName("ROLE_ADMIN");
			adminRole.setDesignation("ADMINISTRATOR");

			Role userRole = new Role();
			userRole.setRoleName("ROLE_USER");
			userRole.setDesignation("STANDARD USER");
			
			Role managerRole = new Role();
			managerRole.setRoleName("ROLE_MANAGER");
			managerRole.setDesignation("MANAGER");
			
			roleRepository.saveAll(List.of(adminRole, userRole, managerRole));
		}

   }
}