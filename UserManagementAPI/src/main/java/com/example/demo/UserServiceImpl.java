package com.example.demo;

import java.util.HashSet;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final OTPClient otpClient;
	
	private final UserEventPublisher userEventPublisher;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, OTPClient otpClient, UserEventPublisher userEventPublisher) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.otpClient = otpClient;
		this.userEventPublisher = userEventPublisher;
	}

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

		if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists: " + userRequestDTO.getEmail());
		}

		if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
			throw new InValidUserException("Username already exists");
		}

		User user = new User();
		user.setUsername(userRequestDTO.getUsername());
		user.setEmail(userRequestDTO.getEmail());
		user.setPassword(userRequestDTO.getPassword());
		user.setFirstName(userRequestDTO.getFirstName());
		user.setLastName(userRequestDTO.getLastName());
		user.setMobileNumber(userRequestDTO.getMobileNumber());
		user.setAddress(userRequestDTO.getAddress());
		user.setProfilePictureUrl(userRequestDTO.getProfilePictureUrl());
		

		User savedUser = userRepository.save(user);

		// ✅ Correct Feign DTO
		OTPRequest otpRequest = new OTPRequest(savedUser.getEmail(), savedUser.getMobileNumber());
		
		try {
			otpClient.sendOTP(otpRequest);
		} catch (Exception e) {
			logger.error("OTP service failed: {}", e.getMessage());

			// 🔥 OPTION 1: Rollback user creation
			throw new RuntimeException("User created but OTP failed. Please retry");

			// 🔥 OPTION 2 (Better in real world):
			// mark user as INACTIVE and retry later
		}

		// ✅ Publish Kafka event after successful user creation and OTP trigger
		userEventPublisher.publishUserCreatedEvent(savedUser);
		logger.info("User created & OTP triggered for ID: {}", savedUser.getUserId());

		return mapToResponseDTO(savedUser);
	}

	private UserResponseDTO mapToResponseDTO(User user) {

		Set<String> roleNames = new HashSet<>();

		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				if (role != null && role.getRoleName() != null) {
					roleNames.add(role.getRoleName());
				}
			}
		}

		return new UserResponseDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getFirstName(),
				user.getLastName(), user.getMobileNumber(), user.getAddress(), roleNames, user.getStatus(),
				user.getProfilePictureUrl());
	}

	@Override
	@Cacheable(value = "userById", key = "#id")
	public UserResponseDTO getUserById(Long id) {
		logger.info("🔥 DATA COMING FROM DATABASE for id: {}", id);

		return userRepository.findById(id).map(this::mapToResponseDTO).orElseThrow(() -> {
			logger.warn("User not found with ID: {}", id);
			return new UserNotFoundException("User not found with ID: " + id);
		});
	}

	@Override
	@CacheEvict(value = "userById", key = "#userId")

	public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {

		User existingUser = userRepository.findById(userId).orElseThrow(() -> {
			logger.warn("Attempt to update non-existent user with ID: {}", userId);
			return new UserNotFoundException("User not found with ID: " + userId);
		});

		if (userRepository.existsByUsername(userRequestDTO.getUsername())
			    && !existingUser.getUsername().equals(userRequestDTO.getUsername())) {
			    throw new InValidUserException("Username already exists");
			}
		if (userRepository.existsByEmail(userRequestDTO.getEmail())
				&& !existingUser.getEmail().equals(userRequestDTO.getEmail())) {

			logger.warn("Attempt to update user with existing email: {}", userRequestDTO.getEmail());
			throw new EmailAlreadyExistsException("Email already exists: " + userRequestDTO.getEmail());
		}

		existingUser.setUsername(userRequestDTO.getUsername());
		existingUser.setEmail(userRequestDTO.getEmail());
		existingUser.setFirstName(userRequestDTO.getFirstName());
		existingUser.setLastName(userRequestDTO.getLastName());
		existingUser.setMobileNumber(userRequestDTO.getMobileNumber());
		existingUser.setAddress(userRequestDTO.getAddress());
		existingUser.setProfilePictureUrl(userRequestDTO.getProfilePictureUrl());
		existingUser.setStatus(userRequestDTO.getStatus());
		existingUser.setPassword(userRequestDTO.getPassword());

		User updatedUser = userRepository.save(existingUser);

		logger.info("Updated user with ID: {}", userId);

		return mapToResponseDTO(updatedUser);
	}

	@Override
	@CacheEvict(value = "users", key = "#userId")

	public void deleteUser(Long id) {

		if (!userRepository.existsById(id)) {
			logger.warn("Attempt to delete non-existent user with ID: {}", id);
			throw new UserNotFoundException("User not found with ID: " + id);
		}
		userRepository.deleteById(id);
		logger.info("Deleted user with ID: {}", id);
	}

	@Override
	public UserResponseDTO assignRoleToUser(Long userId, Long roleId) {

		User user = userRepository.findById(userId).orElseThrow(() -> {
			logger.warn("User not found with ID: {}", userId);
			return new UserNotFoundException("User not found with ID: " + userId);
		});

		Role role = roleRepository.findById(roleId).orElseThrow(() -> {
			logger.warn("Role not found with ID: {}", roleId);
			return new RoleNotFoundException("Role not found with ID: " + roleId);
		});

		if (user.getRoles() == null) {
			user.setRoles(new HashSet<>());
		}
		user.getRoles().add(role);
		User updatedUser = userRepository.save(user);

		logger.info("Assigned role with ID: {} to user with ID: {}", roleId, userId);
		return mapToResponseDTO(updatedUser);
	}

	@Override
	public UserResponseDTO removeRoleFromUser(Long userId, Long roleId) {

		User user = userRepository.findById(userId).orElseThrow(() -> {
			logger.warn("User not found with ID: {}", userId);
			return new UserNotFoundException("User not found with ID: " + userId);
		});

		Role role = roleRepository.findById(roleId).orElseThrow(() -> {
			logger.warn("Role not found with ID: {}", roleId);
			return new UserNotFoundException("Role not found with ID: " + roleId);
		});

		user.getRoles().remove(role);
		User updatedUser = userRepository.save(user);

		logger.info("Removed role with ID: {} from user with ID: {}", roleId, userId);

		return mapToResponseDTO(updatedUser);
	}

	@Override
	public List<UserResponseDTO> getUsersByRole(String roleName) {

		return roleRepository.findByRoleName(roleName)
				.map(role -> userRepository.findByRoles(role).stream().map(this::mapToResponseDTO).toList())
				.orElseThrow(() -> {
					logger.warn("Role not found with name: {}", roleName);
					return new UserNotFoundException("Role not found with name: " + roleName);
				});
	}

	@Override
	public UserResponseDTO findByUsername(String username) {
		return userRepository.findByUsername(username).map(this::mapToResponseDTO).orElseThrow(() -> {
			logger.warn("User not found with username: {}", username);
			return new UserNotFoundException("User not found with username: " + username);
		});
	}

	@Override
	@Cacheable(value = "userByEmail", key = "#email")

	public UserResponseDTO findByEmail(String email) {
		return userRepository.findByEmail(email).map(this::mapToResponseDTO).orElseThrow(() -> {
			logger.warn("User not found with email: {}", email);
			return new UserNotFoundException("User not found with email: " + email);
		});
	}

	@Override
	public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(this::mapToResponseDTO);
	}

	@Override
	public List<UserResponseDTO> getUsersByStatus(UserStatus status) {
		return userRepository.findByStatus(status).stream().map(this::mapToResponseDTO).toList();

	}

}
