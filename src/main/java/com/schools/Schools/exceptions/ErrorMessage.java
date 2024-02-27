package com.schools.Schools.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}
