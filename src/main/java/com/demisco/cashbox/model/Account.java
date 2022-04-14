package com.demisco.cashbox.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String number;
    @ManyToOne
    private Bank bank;
    @ManyToOne
    private Member member;
}
