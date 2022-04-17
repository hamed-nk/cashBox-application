package com.demisco.cashbox.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String nationalCode;
}
