package com.pm.patientservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(
          MethodArgumentNotValidException exception) {
    Map<String, String> fieldErrors = new HashMap<>();

    exception.getBindingResult().getFieldErrors().forEach(
            fieldError -> fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));

    return ResponseEntity.badRequest().body(fieldErrors);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(
          EmailAlreadyExistsException exception
  ) {
    log.warn("Email address already exists {}", exception.getMessage());

    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Email already exists");
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(PatientNotFoundException.class)
  public ResponseEntity<Map<String, String>> handlePatientNotFoundException(
          PatientNotFoundException exception) {
    log.warn("Patient not found {}", exception.getMessage());

    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Patient not found");
    return ResponseEntity.badRequest().body(errors);
  }
}
