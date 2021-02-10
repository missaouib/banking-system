package com.ironhack.bankingsystem.controller.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import com.ironhack.bankingsystem.service.impl.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @GetMapping("v1/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    @GetMapping("v1/accounts/{accountHolderId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findByOwnerId(@PathVariable Integer accountHolderId){
        return accountService.viewAccountsById(accountHolderId);
    }

    @GetMapping("v1/accounts/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findByStatus(@PathVariable(name="status") String status){
        return accountRepository.findByStatus(Status.valueOf(status.toUpperCase()));
    }

    @GetMapping("/v1/account/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public BalanceDTO accessBalanceByAccountId(@PathVariable(name = "id") Integer accountId) {
        return accountService.checkBalance(accountId);
    }
}
