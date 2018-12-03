package com.arma.inz.compcal.users;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String email;
    private String firstName;
    private String surname;
    private String company;
    private String nip;
    private String street;
    private String parcelNo;
    private String homeNo;
    private String zip;
    private String city;
    private String password;
}
