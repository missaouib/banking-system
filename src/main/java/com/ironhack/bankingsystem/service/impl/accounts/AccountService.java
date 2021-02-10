package com.ironhack.bankingsystem.service.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.exceptions.NoPresentAccountHolder;
import com.ironhack.bankingsystem.exceptions.UnauthorizedAccess;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;


    public List<Account> viewAccountsById(Integer accountHolderId){
        AccountHolder owner = accountHolderRepository.findById(accountHolderId).orElseThrow(NoPresentAccountHolder::new);
        return accountRepository.findByPrimaryOwnerOrSecondaryOwner(owner, owner);

    }


    /** service to check the balance */

    public BalanceDTO checkBalance(Integer accountId, UserDetails user) {

        Account account = accountRepository.findById(accountId).orElseThrow(NoPresentAccount::new);
        account.isOwnedBy(user.getUsername());

        if (account instanceof Checking) {
            return new BalanceDTO(account.getBalance());

        } else if (account instanceof Savings) {
            ((Savings) account).applyInterestRate();
            accountRepository.save(account);
            return new BalanceDTO(account.getBalance());

        } else if (account instanceof StudentChecking) {
            return new BalanceDTO(account.getBalance());

        } else {
            ((CreditCard) account).applyInterestRate();
            accountRepository.save(account);
            return new BalanceDTO(account.getBalance());
        }
    }


}
