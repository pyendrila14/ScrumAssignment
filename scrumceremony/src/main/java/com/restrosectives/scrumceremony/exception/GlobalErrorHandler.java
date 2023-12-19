package com.restrosectives.scrumceremony.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @ExceptionHandler(FeedBackNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(FeedBackNotFoundException ex) {
        String message =  ex.getMessage();
       LOG.error(message, ex);

        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions
    (Exception ex) {
        String message = ex.getMessage();
        LOG.error(message, ex);
        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
