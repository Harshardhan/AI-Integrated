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
			adminRole.setRoleName("ADMIN");
			adminRole.setDesignation("ADMINISTRATOR");
			roleRepository.save(adminRole);

			Role userRole = new Role();
			userRole.setRoleName("USER");
			userRole.setDesignation("STANDARD USER");
			roleRepository.save(userRole);
			
			Role managerRole = new Role();
			managerRole.setRoleName("MANAGER");
			managerRole.setDesignation("MANAGER");
			roleRepository.save(managerRole);
			
			roleRepository.saveAll(List.of(adminRole, userRole, managerRole));
		}

   }
}