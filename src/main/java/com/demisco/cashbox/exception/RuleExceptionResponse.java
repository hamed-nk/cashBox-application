package com.demisco.cashbox.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleExceptionResponse {
    private String error;
}
