package com.Preparation.SpringBootProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundHandler(ResourceNotFoundException ex, WebRequest req)
    {
      Map<String,Object> body= new LinkedHashMap<>();
      body.put("TimeStamp", LocalDateTime.now());
      body.put("Status", HttpStatus.NOT_FOUND.value());
      body.put("Error",ex.getMessage());
      body.put("Path",((ServletWebRequest)req).getRequest().getRequestURI());
      return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
    }
}
