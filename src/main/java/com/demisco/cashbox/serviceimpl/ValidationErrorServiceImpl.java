package com.demisco.cashbox.serviceimpl;

import com.demisco.cashbox.service.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorServiceImpl implements ValidationErrorService {
    @Override
    public ResponseEntity<?> validate(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors())
                errors.put(error.getField(), error.getDefaultMessage());

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
