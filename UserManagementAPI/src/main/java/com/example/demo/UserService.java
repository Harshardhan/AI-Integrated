package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO)throws InValidUserException, UserNotFoundException, EmailAlreadyExistsException;

    UserResponseDTO getUserById(Long id)throws UserNotFoundException;

    UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO)throws UserNotFoundException, EmailAlreadyExistsException;

    void deleteUser(Long id)throws UserNotFoundException;

    UserResponseDTO assignRoleToUser(Long userId, Long roleId);

    UserResponseDTO removeRoleFromUser(Long userId, Long roleId);

    List<UserResponseDTO> getUsersByRole(String roleName);

    UserResponseDTO findByUsername(String username)throws UserNotFoundException;

    UserResponseDTO findByEmail(String email);

    Page<UserResponseDTO> getAllUsers(Pageable pageable);

    List<UserResponseDTO> getUsersByStatus(UserStatus status);

	void activateUser(String emailId);



}