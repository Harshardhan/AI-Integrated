package com.example.demo;

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
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
		StringBuilder errors = new StringBuilder();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			errors.append(error.getDefaultMessage()).append("; ");
		});
		logger.error("Validation error: {}", errors.toString());
		return buildErrorResponse("Validation Error", errors.toString(), HttpStatus.BAD_REQUEST, request);

	}
	
	
	
    // Common builder method
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            String errorTitle, String message, HttpStatus status, WebRequest request) {

        ErrorResponse error = new ErrorResponse(
        						status.value(),
				message,
				errorTitle,
				System.currentTimeMillis(),
				errorTitle,
				request.getDescription(false).replace("uri=", "")
		);
        return new ResponseEntity<>(error, status);
    }

}
