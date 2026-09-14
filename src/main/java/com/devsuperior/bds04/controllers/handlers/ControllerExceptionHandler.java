package com.devsuperior.bds04.controllers.handlers;

import com.devsuperior.bds04.errors.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now(),
                httpStatus.value(),
                e.getMessage(),
                request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            validationError.addErrors(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(httpStatus).body(validationError);
    }
}
