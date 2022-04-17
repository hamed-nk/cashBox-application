package com.demisco.cashbox.controller;

import com.demisco.cashbox.request.AccountRequest;
import com.demisco.cashbox.response.Response;
import com.demisco.cashbox.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.save(request));
    }

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("accounts", accountService.getAll()))
                        .message("get all accounts")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("{memberId}")
    public ResponseEntity<Response> getByMember(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("accounts", accountService.getByMember(memberId)))
                        .message("get all accounts")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
