package com.demisco.cashbox.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class FinancialTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    private Double amount;
    private LocalDateTime transactionDate;
    @ManyToOne
    private Account account;
    @ManyToOne
    private BankReceipt bankReceipt;
    @ManyToOne
    private Month month;
    @ManyToOne
    private TransactionStatus transactionStatus;
}
