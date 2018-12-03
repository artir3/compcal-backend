package com.arma.inz.compcal.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(unique=true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "name")
    private String name;

    @Column(name = "nip")
    private String nip;

    @Column(name = "surname")
    private String surname;

    @Column(name = "active")
    private boolean active;

    @Column(name = "hash")
    private String hash;

    @Enumerated(EnumType.STRING)
    private RolesEnum roles;

}