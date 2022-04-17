package com.demisco.cashbox.repository;

import com.demisco.cashbox.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(String number);

    List<Account> findByMemberId(Long memberId);
}
