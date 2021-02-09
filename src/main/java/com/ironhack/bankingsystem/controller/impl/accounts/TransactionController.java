package com.ironhack.bankingsystem.controller.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.service.impl.accounts.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("v1/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeTransaction (@RequestBody @Valid TransactionDTO transactiondto){
        transactionService.transferMoney(transactiondto);
    }





}
