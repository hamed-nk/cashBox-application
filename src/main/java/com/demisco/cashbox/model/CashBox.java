package com.demisco.cashbox.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CashBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    @ManyToOne
    private Account account;
}
