package com.demisco.cashbox.service;

import com.demisco.cashbox.request.AccountRequest;
import com.demisco.cashbox.response.AccountResponse;

import java.util.List;

public interface AccountService {
    Long save(AccountRequest request);

    List<AccountResponse> getAll();

    List<AccountResponse> getByMember(Long memberId);
}
