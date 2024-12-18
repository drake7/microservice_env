package com.drake7v.accounts.service;

import com.drake7v.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
    *
    *
    * @param customerDto - customerDtoObject
    * */
    void createAccount(CustomerDto customerDto);
}
