package com.demisco.cashbox.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberRequest {
    private Long id;
    @NotBlank(message = "نام خالی است")
    private String firstName;
    @NotBlank(message = "نام خانوادگی خالی است")
    private String lastName;
    private String mobile;
    @NotBlank(message = "کد ملی خالی است")
    private String nationalCode;
}
