package com.drake7v.accounts.dto;


import com.drake7v.accounts.repository.AccountsRepository;
import lombok.Data;

@Data
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;

    private AccountsDto accountsDto;
}
