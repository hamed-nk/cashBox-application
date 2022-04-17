package com.demisco.cashbox.controller;

import com.demisco.cashbox.request.MemberRequest;
import com.demisco.cashbox.service.MemberService;
import com.demisco.cashbox.service.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/member")
public class MemberController {
    private final MemberService memberService;
    private final ValidationErrorService validationErrorService;

    public MemberController(MemberService memberService, ValidationErrorService validationErrorService) {
        this.memberService = memberService;
        this.validationErrorService = validationErrorService;
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MemberRequest request, BindingResult result) {
        ResponseEntity<?> errors = validationErrorService.validate(result);
        if (errors != null) return errors;
        return new ResponseEntity<>(memberService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody MemberRequest request, BindingResult result) {
        ResponseEntity<?> errors = validationErrorService.validate(result);
        if (errors != null) return errors;
        request.setId(id);
        return new ResponseEntity<>(memberService.save(request), OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(memberService.getAll(), OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.delete(id), OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.findById(id), OK);
    }
}
