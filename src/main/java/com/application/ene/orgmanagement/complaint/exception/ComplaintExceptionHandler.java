package com.application.ene.orgmanagement.complaint.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ComplaintExceptionHandler {

    @ExceptionHandler(ComplaintServiceException.class)
    public ResponseEntity<Map<String, String>> handleComplaintException(ComplaintServiceException e) {
        var response = Map.of("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
