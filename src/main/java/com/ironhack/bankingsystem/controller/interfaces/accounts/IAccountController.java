package com.ironhack.bankingsystem.controller.interfaces.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IAccountController {

    public List<Account> findAll();

    public List<Account> findAccounts(@AuthenticationPrincipal CustomUserDetails user);

    public List<Account> findByStatus(String status);

    public BalanceDTO accessBalanceByAccountId(UserDetails user, Integer accountId);

}
