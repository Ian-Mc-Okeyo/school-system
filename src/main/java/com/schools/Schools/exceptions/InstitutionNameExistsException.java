package com.schools.Schools.exceptions;

public class InstitutionNameExistsException extends RuntimeException{
    public InstitutionNameExistsException(String message){
        super(message);
    }
}
