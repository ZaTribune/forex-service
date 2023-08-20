package com.zatribune.forex.error;


import com.zatribune.forex.domain.GenericResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<GenericResponse<Object>> handleException(ResponseStatusException ex) {

        String extra = null;
        String message = "ERROR";
        if (ex.getCause() != null) {
            extra = ex.getCause().getMessage();
            if (!StringUtils.defaultString(ex.getMessage()).contains(extra)) {
                message = ex.getMessage();
            }
        }

        GenericResponse<Object> response = GenericResponse.builder()
                .message(message)
                .reason(extra)
                .code(5000)
                .build();

        return ResponseEntity.status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        GenericResponse<Object> response = GenericResponse.builder()
                .message("Invalid Input")
                .reason(errors)
                .code(4001)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
