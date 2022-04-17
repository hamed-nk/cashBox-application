package com.demisco.cashbox.repository;

import com.demisco.cashbox.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNationalCode(String NationalCode);
}
