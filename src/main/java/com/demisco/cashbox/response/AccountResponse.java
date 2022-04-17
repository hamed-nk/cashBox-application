package com.demisco.cashbox.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long id;
    private String name;
    private String number;
    private String bankName;
    private String memberName;
}
