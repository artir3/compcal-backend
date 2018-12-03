package com.arma.inz.compcal.users;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "accountNo")
    private String accountNo;
    @Column
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "baseUserId")
    private BaseUser baseUser;

}
