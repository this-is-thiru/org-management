package com.application.ene.orgmanagement.common.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Log4j2
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleComplaintException(IllegalArgumentException e) {
        log.error("IllegalArgumentException occurred: ", e);
        var response = Map.of("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleComplaintException(RuntimeException  e) {
        log.error("RuntimeException occurred: ", e);
        var response = Map.of("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
