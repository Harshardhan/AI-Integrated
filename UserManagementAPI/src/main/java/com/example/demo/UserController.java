package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping()
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
		logger.info("Received request to create user: {}", userRequestDTO.getUsername());
		UserResponseDTO createdUser = userService.createUser(userRequestDTO);
		return ResponseEntity.ok(createdUser);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
			@RequestBody @Valid UserRequestDTO userRequestDTO) {
		logger.info("Received request to update user: {}", userRequestDTO.getUsername());
		UserResponseDTO updatedUser = userService.updateUser(userId, userRequestDTO);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		logger.info("Received request to delete user with ID: {}", userId);
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
		logger.info("Received request to get user with ID: {}", userId);
		UserResponseDTO user = userService.getUserById(userId);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
		logger.info("Received request to get user with email: {}", email);
		UserResponseDTO user = userService.findByEmail(email);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
		logger.info("Received request to get user with username: {}", username);
		UserResponseDTO user = userService.findByUsername(username);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/role/{roleName}")
	public ResponseEntity<List<UserResponseDTO>> getUsersByRole(@PathVariable String roleName) {
		logger.info("Received request to get users with role: {}", roleName);
		List<UserResponseDTO> users = userService.getUsersByRole(roleName);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<UserResponseDTO>> getUsersByStatus(@PathVariable UserStatus status) {
		logger.info("Received request to get users with status: {}", status);
		List<UserResponseDTO> users = userService.getUsersByStatus(status);
		return ResponseEntity.ok(users);
	}

	@PostMapping("/{userId}/roles/{roleId}")
	public ResponseEntity<UserResponseDTO> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
		logger.info("Received request to assign role with ID: {} to user with ID: {}", roleId, userId);
		UserResponseDTO updatedUser = userService.assignRoleToUser(userId, roleId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userId}/roles/{roleId}")
	public ResponseEntity<UserResponseDTO> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
		logger.info("Received request to remove role with ID: {} from user with ID: {}", roleId, userId);
		UserResponseDTO updatedUsers = userService.removeRoleFromUser(userId, roleId);
		return ResponseEntity.ok(updatedUsers);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers(Pageable pageable) {

	    List<UserResponseDTO> users =
	        userService.getAllUsers(pageable).getContent();

	    return ResponseEntity.ok(users);
	}	
}