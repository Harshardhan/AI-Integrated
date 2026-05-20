package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(String roleName);

	boolean existsByRoleName(String roleName);

	long countByUsers(User user);

	List<Role> findByUsers(User user);
}
