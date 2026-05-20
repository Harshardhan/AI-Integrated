package com.example.demo;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
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


    // 1️⃣ Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildErrorResponse("Validation Error", errors, HttpStatus.BAD_REQUEST, request);
    }


    // 3️⃣ Business Logic Errors
    @ExceptionHandler(InValidOrderException.class)
    public ResponseEntity<ErrorResponse> handleInValidOrder(InValidOrderException ex, WebRequest request) {
        return buildErrorResponse(" InValid Order", ex.getMessage(), HttpStatus.CONFLICT, request);
    }


    // Common builder method
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            String errorTitle, String message, HttpStatus status, WebRequest request) {

        ErrorResponse error = new ErrorResponse(
                errorTitle,
                message,
                status.value(),
                LocalDateTime.now(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, status);
    }

}
