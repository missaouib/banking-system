package com.ironhack.bankingsystem.controller.interfaces.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.model.account.Account;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IAccountController {

    public List<Account> findAll();

    public List<Account> findByOwnerId(Integer accountHolderId);

    public List<Account> findByStatus(String status);

    public BalanceDTO accessBalanceByAccountId(UserDetails user, Integer accountId);

}
