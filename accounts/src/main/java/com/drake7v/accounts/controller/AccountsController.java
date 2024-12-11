package com.drake7v.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/")
public String sayHello()
{
    return "Hello deepak from microservices.";
}
}
