package com.demisco.cashbox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomRuleExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handlerRuleException(RuleException exception, WebRequest request) {
        RuleExceptionResponse response = new RuleExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
