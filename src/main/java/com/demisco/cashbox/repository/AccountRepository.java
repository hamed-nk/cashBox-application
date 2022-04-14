package com.demisco.cashbox.repository;

import com.demisco.cashbox.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
