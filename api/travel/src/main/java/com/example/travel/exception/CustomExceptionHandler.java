package com.example.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class CustomExceptionHandler { // todo: maybe extends ResponseEntityExceptionHandler

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<Object> handleRequestException(RequestException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Optional<FieldError> firstFieldError = ex.getBindingResult().getFieldErrors().stream().findFirst();
        String message = firstFieldError.isPresent()
                ? firstFieldError.get().getDefaultMessage()
                : ex.getMessage();

        return buildExceptionResponse(message, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildExceptionResponse(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(message, httpStatus);
    }
}
