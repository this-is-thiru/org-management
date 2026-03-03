package com.application.ene.orgmanagement.complaint.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Log4j2
@RestControllerAdvice
public class ComplaintExceptionHandler {

    @ExceptionHandler(ComplaintServiceException.class)
    public ResponseEntity<Map<String, String>> handleComplaintException(ComplaintServiceException e) {
        log.error("ComplaintServiceException occurred: ", e);
        var response = Map.of("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
