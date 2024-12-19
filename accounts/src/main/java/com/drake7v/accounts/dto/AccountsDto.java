package com.drake7v.accounts.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data@Getter@Setter
public class AccountsDto {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;

}
