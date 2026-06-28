package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByMobileNumber(String mobileNumber);
	
	long countByRoles_RoleName(String roleName);	

	List<User> findByRoles(Role role);
	
	List<User> findByStatus(UserStatus status);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

	boolean existsByMobileNumber(String mobileNumber);

}
