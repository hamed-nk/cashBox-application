package com.demisco.cashbox.request;

import lombok.Data;

@Data
public class AccountRequest {
    private String name;
    private String number;
    private String bankCode;
    private String nationalCode;
}
