package com.demisco.cashbox.service;

import com.demisco.cashbox.request.MemberRequest;
import com.demisco.cashbox.response.MemberResponse;

import java.util.List;

public interface MemberService {
    Long save(MemberRequest request);

    List<MemberResponse> getAll();

    Boolean delete(Long id);

    MemberResponse findById(Long id);
}
