package com.arma.inz.compcal.users.dto;

import lombok.Data;

@Data
public class BankAccountDTO {
    private Long id;
    private String accountNo;
    private String currency;
    private String swift;
}
