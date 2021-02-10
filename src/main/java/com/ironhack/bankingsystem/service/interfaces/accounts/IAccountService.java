package com.ironhack.bankingsystem.service.interfaces.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.model.account.Account;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IAccountService {

    public List<Account> viewAccountsById(Integer accountHolderId);
    public BalanceDTO checkBalance(Integer accountId, UserDetails user);
}
