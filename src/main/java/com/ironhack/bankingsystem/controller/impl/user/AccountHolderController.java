package com.ironhack.bankingsystem.controller.impl.user;

import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.service.impl.user.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AccountHolderController {

    @Autowired
    private AccountHolderService accountHolderService;

    @PostMapping("v1/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeTransaction (@RequestBody @Valid TransactionDTO transactiondto){
        accountHolderService.transferMoney(transactiondto);
    }




}
