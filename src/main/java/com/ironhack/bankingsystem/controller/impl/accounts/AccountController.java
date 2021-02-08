package com.ironhack.bankingsystem.controller.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.service.impl.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;


    @GetMapping("/v1/account/{id}/Balance")
    @ResponseStatus(HttpStatus.OK)
    public BalanceDTO accessBalanceByAccountId(@PathVariable(name = "id") Integer accountId) {
        return accountService.checkBalance(accountId);
    }
}
