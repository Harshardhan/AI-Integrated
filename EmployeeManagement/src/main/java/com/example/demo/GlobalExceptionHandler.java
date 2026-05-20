package com.example.demo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice

public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex, WebRequest request) {
		logger.error("Employee not found: {}", ex.getMessage());

		return buildErrorResponse("Employee Not Found", ex.getMessage(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(InvalidEmployeeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidEmployee(InvalidEmployeeException ex, WebRequest request) {
		logger.error("Invalid employee data: {}", ex.getMessage());
		return buildErrorResponse("Invalid Employee Data", ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex,
			WebRequest request) {
		logger.error("Employee already exists: {}", ex.getMessage());
		return buildErrorResponse("Employee Already Exists", ex.getMessage(), HttpStatus.CONFLICT, request);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
		logger.error("Invalid argument: {}", ex.getMessage());

		return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Argument", ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ""));
	}

	// Common builder method
	private ResponseEntity<ErrorResponse> buildErrorResponse(String errorTitle, String message, HttpStatus status,
			WebRequest request) {

		ErrorResponse error = new ErrorResponse(errorTitle, message, status.value(), LocalDateTime.now(),
				request.getDescription(false).replace("uri=", ""));
		return new ResponseEntity<>(error, status);
	}

}
