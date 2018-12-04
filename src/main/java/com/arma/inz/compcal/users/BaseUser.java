package com.arma.inz.compcal.users;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table
public class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "active")
    private boolean active;
    @Column(name = "hash")
    private String hash;
    @Column
    @Enumerated(EnumType.STRING)
    private RolesEnum roles;
    @Column(unique=true, name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "company")
    private String company;
    @Column(name = "nip")
    private String nip;
    @Column(name = "surname")
    private String surname;
    @Column(name = "street")
    private String street;
    @Column(name = "parcelNo")
    private String parcelNo;
    @Column(name = "homeNo")
    private String homeNo;
    @Column(name = "zip")
    private String zip;
    @Column(name = "city")
    private String city;
    @Column
    @Enumerated(EnumType.STRING)
    private TaxFormEnum taxForm;
    @Column
    @Enumerated(EnumType.STRING)
    private VatFormEnum vatForm;
    @Column(name = "pkd")
    private String pkd;
    @Column(name = "regon")
    private String regon;
    @OneToMany(mappedBy = "baseUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BankAccount> bankAccountSet;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime modifiedAt;
    @Column(name = "country")
    private String country;

}