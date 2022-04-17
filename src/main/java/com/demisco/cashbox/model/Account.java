package com.demisco.cashbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
