package com.arma.inz.compcal.users.dto;

import com.arma.inz.compcal.users.CurrencyEnum;
import lombok.Data;

@Data
public class BankAccountDTO {
    private Long id;
    private String accountNo;
    private CurrencyEnum currency;
}
