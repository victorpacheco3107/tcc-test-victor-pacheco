package com.tcc.victorpacheco.application.controller;

import com.tcc.victorpacheco.application.dto.ErrorResponse;
import com.tcc.victorpacheco.application.constant.ErrorMessage;
import com.tcc.victorpacheco.domain.exception.ClientAlreadyExistException;
import com.tcc.victorpacheco.domain.exception.ClientNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ClientNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleClientNotFound(ClientNotFoundException ex){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.buildSingleError(
                String.format(
                    ErrorMessage.MESSAGE_CLIENT_NOT_FOUND,
                    ex.getClient().getIdentification(),
                    ex.getClient().getIdentificationType()
                ))
            );
    }

    @ExceptionHandler(value = ClientAlreadyExistException.class)
    protected ResponseEntity<ErrorResponse> handleClientAlreadyExist(ClientAlreadyExistException ex){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.buildSingleError(
                String.format(
                    ErrorMessage.MESSAGE_CLIENT_ALREADY_EXIST,
                    ex.getClient().getIdentification(),
                    ex.getClient().getIdentificationType()
                ))
            );
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex){
        List<String> errors = ex.getConstraintViolations().stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}
