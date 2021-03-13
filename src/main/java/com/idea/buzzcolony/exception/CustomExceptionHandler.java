package com.idea.buzzcolony.exception;

import com.idea.buzzcolony.util.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 02/04/20
 */

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ApiResponse error = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getLocalizedMessage());
        ex.printStackTrace();
        return new ResponseEntity(error, error.getStatus());
    }
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        ApiResponse error = new ApiResponse(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), details);
        return new ResponseEntity(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ApiResponse error = new ApiResponse(HttpStatus.BAD_REQUEST, "Validation Failed", details);
        return new ResponseEntity(error, error.getStatus());
    }
}
