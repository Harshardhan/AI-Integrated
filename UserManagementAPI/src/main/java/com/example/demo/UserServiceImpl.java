package com.example.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder;

	private final RoleRepository roleRepository;

	private final OTPClient otpClient;

	private final UserEventPublisher userEventPublisher;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, OTPClient otpClient,
			UserEventPublisher userEventPublisher, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.otpClient = otpClient;
		this.userEventPublisher = userEventPublisher;
	}

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

		// =========================
		// VALIDATIONS
		// =========================

		if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists: " + userRequestDTO.getEmail());
		}

		if (userRepository.existsByMobileNumber(userRequestDTO.getMobileNumber())) {
			throw new InValidUserException("Mobile number already exists: " + userRequestDTO.getMobileNumber());
		}

		if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
			throw new InValidUserException("Username already exists");
		}

		// =========================
		// CREATE USER
		// =========================

		User user = new User();

		user.setUsername(userRequestDTO.getUsername());
		user.setEmail(userRequestDTO.getEmail());

		// Encrypt password
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

		user.setFirstName(userRequestDTO.getFirstName());
		user.setLastName(userRequestDTO.getLastName());
		user.setMobileNumber(userRequestDTO.getMobileNumber());
		user.setAddress(userRequestDTO.getAddress());
		user.setProfilePictureUrl(userRequestDTO.getProfilePictureUrl());

		// Default status until OTP verification
		user.setStatus(UserStatus.PENDING_VERIFICATION);

		// Default flags
		user.setActive(false);
		user.setLocked(false);
		user.setExpired(false);

		// No roles assigned yet, will be done after OTP verification
		user.setRoles(new HashSet<>());

		// =========================
		// SAVE USER
		// =========================

		User savedUser = userRepository.save(user);

		logger.info("User created successfully with ID: {}", savedUser.getUserId());

		// =========================
		// SEND OTP
		// =========================

		OTPRequest otpRequest = new OTPRequest(savedUser.getEmail(), savedUser.getMobileNumber());

		try {

			otpClient.sendOTP(otpRequest);

			logger.info("OTP sent successfully for user ID: {}", savedUser.getUserId());

		} catch (Exception e) {

			logger.error("OTP service failed for user ID: {} : {}", savedUser.getUserId(), e.getMessage());

			// Optional:
			// keep user in pending state and retry later

			throw new RuntimeException("OTP service unavailable. Please try again later.");
		}

		// =========================
		// PUBLISH EVENT
		// =========================

		userEventPublisher.publishUserCreatedEvent(savedUser);

		logger.info("USER_REGISTERED event published for user ID: {}", savedUser.getUserId());

		// =========================
		// RETURN RESPONSE
		// =========================

		return mapToResponseDTO(savedUser);
	}

	private UserResponseDTO mapToResponseDTO(User user) {

		Set<String> roleNames = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet());

		return new UserResponseDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getFirstName(),
				user.getLastName(), user.getMobileNumber(), user.getAddress(), roleNames, // <-- use role names
				user.getStatus(), user.getProfilePictureUrl());
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
	@CacheEvict(value = "userById", key = "#id")
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
		existingUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

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

	@Override
	public void activateUser(String emailId) {

		User user = userRepository.findByEmail(emailId).orElseThrow(() -> {
			logger.warn("User not found with email: {}", emailId);
			return new UserNotFoundException("User not found with email: " + emailId);
		});

		user.setStatus(UserStatus.ACTIVE);
		user.setActive(true);
		userRepository.save(user);

		logger.info("Activated user with email: {}", emailId);
	}

}
