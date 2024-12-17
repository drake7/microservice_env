package com.drake7v.accounts.controller;

import com.drake7v.accounts.dto.CustomerDto;
import com.drake7v.accounts.dto.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

@GetMapping("/")
public String sayHello()
{
    return "Hello deepak from microservices.";
}


public ResponseEntity<ResponseDto>  createAccount(@RequestBody CustomerDto customerDto){


    return null;
}
}
