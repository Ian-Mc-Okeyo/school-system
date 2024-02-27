package com.schools.Schools.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler {
    @ExceptionHandler(InstitutionNameExistsException.class)
    public ResponseEntity<ErrorMessage> institutionNameExistsException(InstitutionNameExistsException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(InstitutionDoesNotExistException.class)
    public ResponseEntity<ErrorMessage> institutionDoesNotExistException(InstitutionDoesNotExistException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(CourseExistsException.class)
    public ResponseEntity<ErrorMessage> courseExistsException(CourseExistsException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorMessage> courseNotFoundException(CourseNotFoundException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ForbiddenActionsException.class)
    public ResponseEntity<ErrorMessage> forbiddenActionsException(ForbiddenActionsException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorMessage> studentNotFoundException(StudentNotFoundException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

}
