package com.demisco.cashbox.serviceimpl;

import com.demisco.cashbox.exception.RuleException;
import com.demisco.cashbox.model.Account;
import com.demisco.cashbox.model.Bank;
import com.demisco.cashbox.repository.AccountRepository;
import com.demisco.cashbox.repository.MemberRepository;
import com.demisco.cashbox.request.AccountRequest;
import com.demisco.cashbox.response.AccountResponse;
import com.demisco.cashbox.service.AccountService;
import com.demisco.cashbox.service.DoaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final DoaService doaService;

    public AccountServiceImpl(AccountRepository accountRepository, MemberRepository memberRepository, DoaService doaService) {
        this.accountRepository = accountRepository;
        this.memberRepository = memberRepository;
        this.doaService = doaService;
    }

    @Override
    public Long save(AccountRequest request) {
        validationBeforeMap(request);
        Account account = mapToEntity(request);
        Account save = accountRepository.save(account);
        return save.getId();
    }

    private void validationBeforeMap(AccountRequest request) {
        Optional<Account> account = accountRepository.findByNumber(request.getNumber());
        if (account.isPresent())
            throw new RuleException("حساب قبلا ثبت شده است");
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> getAll() {
        return accountRepository.findAll().stream().map(
                        account -> AccountResponse.builder()
                                .name(account.getName())
                                .number(account.getNumber())
                                .bankName(account.getBank().getName())
                                .memberName(account.getMember().getLastName())
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> getByMember(Long memberId) {
        return accountRepository.findByMemberId(memberId).stream().map(
                        account -> AccountResponse.builder()
                                .name(account.getName())
                                .number(account.getNumber())
                                .bankName(account.getBank().getName())
                                .memberName(account.getMember().getLastName())
                                .build())
                .collect(Collectors.toList());
    }

    private Account mapToEntity(AccountRequest request) {
        return Account.builder()
                .name(request.getName())
                .number(request.getNumber())
                .member(memberRepository.findByNationalCode(request.getNationalCode()).orElseThrow(() -> new RuleException("عضو یافت نشد")))
                .bank(doaService.findByCode(Bank.class, request.getBankCode()).orElseThrow(() -> new RuleException("بانک یافت نشد")))
                .build();
    }
}
