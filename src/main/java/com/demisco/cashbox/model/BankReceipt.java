package com.demisco.cashbox.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class BankReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String BankName;
    private LocalDateTime fishDate;
    private String terminalNumber;
    private String trackNumber;
    @ManyToOne
    private Member member;
}
