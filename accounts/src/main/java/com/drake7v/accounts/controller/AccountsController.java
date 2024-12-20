package com.drake7v.accounts.controller;

import com.drake7v.accounts.constants.AccountsConstants;
import com.drake7v.accounts.dto.CustomerDto;
import com.drake7v.accounts.dto.ResponseDto;
import com.drake7v.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

@GetMapping("/")
public String sayHello()
{
    return "Hello deepak from microservices.";
}

private IAccountService iAccountService;
public ResponseEntity<ResponseDto>  createAccount(@RequestBody CustomerDto customerDto){
    iAccountService.createAccount(customerDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
}
}
