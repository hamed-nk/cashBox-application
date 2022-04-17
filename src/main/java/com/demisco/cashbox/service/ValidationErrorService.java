package com.demisco.cashbox.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ValidationErrorService {
    ResponseEntity<?> validate (BindingResult result);
}
