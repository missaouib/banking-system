package com.ironhack.bankingsystem.controller.impl.accounts;

import com.ironhack.bankingsystem.controller.interfaces.accounts.IAccountController;
import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController implements IAccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/v1/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/v1/accounts/{accountHolderId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccounts(@AuthenticationPrincipal CustomUserDetails user) {
        return accountService.viewAccountsByUsername(user.getUsername());
    }

    @GetMapping("/v1/accounts/{status}/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findByStatus(@PathVariable(name = "status") String status) {
        return accountService.findByStatus(Status.valueOf(status.toUpperCase()));
    }

    @GetMapping("/v1/account/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public BalanceDTO accessBalanceByAccountId(@AuthenticationPrincipal UserDetails user, @PathVariable(name = "id") Integer accountId) {
        return accountService.checkBalance(accountId, user);
    }
}
