package com.example.demo;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, "USER_NOT_FOUND", request.getRequestURI());

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDatabaseException(
	        DataIntegrityViolationException ex,
	        HttpServletRequest request) {

	    return buildErrorResponse(
	        "Duplicate entry or constraint violation",
	        HttpStatus.CONFLICT,
	        "DB_CONSTRAINT_ERROR",
	        request.getRequestURI()
	    );
	}
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoleNotFoundException(
	        RoleNotFoundException ex, HttpServletRequest request) {

	    return buildErrorResponse(
	        ex.getMessage(),
	        HttpStatus.NOT_FOUND,
	        "ROLE_NOT_FOUND",
	        request.getRequestURI()
	    );
	}

	@ExceptionHandler(InValidUserException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUserDataException(InValidUserException ex, HttpServletRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "INVALID_USER_DETAILS", request.getRequestURI());
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, HttpServletRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT, "EMAIL_ALREADY_EXISTS", request.getRequestURI());

	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {

	    ex.printStackTrace(); // 🔥 ADD THIS

	    return buildErrorResponse(
	            ex.getMessage(), // show real error
	            HttpStatus.INTERNAL_SERVER_ERROR,
	            "INTERNAL_ERROR",
	            request.getRequestURI()
	    );
	}	private ResponseEntity<ErrorResponse> buildErrorResponse(
	        String message,
	        HttpStatus status,
	        String errorCode,
	        String path) {

	    ErrorResponse error = new ErrorResponse(
	            status.value(),
	            message,
	            errorCode,
	            System.currentTimeMillis(),
	            status.getReasonPhrase(),
	            path
	    );

	    return ResponseEntity.status(status).body(error);
	}

}