package com.zakaria.HealthLinkService.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

  /*  @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex) {
       log.info("herer ..............");
        log.info(HttpStatus.NOT_FOUND.toString());
        log.info(ex.getMessage());
        System.out.println("here    zzzz");

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {CustomResourceNotFoundException.class, NoHandlerFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {

        String bodyOfResponse = "Resource Not Found: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
/*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Handle 404 Not Found errors
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        String bodyOfResponse = "Resource Not Found: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
*/
/*
// Handle 500 Internal Server Error errors
@ExceptionHandler(value = {Exception.class})
protected ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {
    String bodyOfResponse = "Internal Server Error: " + ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
}*/

    // Handle 403 Forbidden errors
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }



    static class ApiError {
        private HttpStatus status;
        private String message;
        private LocalDateTime timestamp;

        public ApiError(HttpStatus status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}
