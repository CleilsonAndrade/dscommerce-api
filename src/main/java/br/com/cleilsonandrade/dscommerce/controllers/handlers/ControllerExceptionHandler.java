package br.com.cleilsonandrade.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cleilsonandrade.dscommerce.dto.CustomErrorDTO;
import br.com.cleilsonandrade.dscommerce.dto.ValidationErrorDTO;
import br.com.cleilsonandrade.dscommerce.services.exceptions.DatabaseException;
import br.com.cleilsonandrade.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomErrorDTO> resourceNotFoundException(ResourceNotFoundException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<CustomErrorDTO> database(DatabaseException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Invalid data",
        request.getRequestURI());

    for (FieldError f : e.getBindingResult().getFieldErrors()) {
      err.addError(f.getField(), f.getDefaultMessage());
    }

    return ResponseEntity.status(status).body(err);
  }
}
