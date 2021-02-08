package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.exceptions.InsufficientFunds;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.transaction.Transaction;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.transaction.TransactionRepository;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public void transferMoney(TransactionDTO transactiondto) {

        Account origin = accountRepository.findById(transactiondto.getOriginAccountId()).orElseThrow(NoPresentAccount::new);
        Account receiver = accountRepository.findById(transactiondto.getReceiverAccountId()).orElseThrow(NoPresentAccount::new);

        receiver.isOwnedBy(transactiondto.getNameOwner());

        if (origin.getBalance().getAmount().compareTo(transactiondto.getAmount()) < 0) {
            throw new InsufficientFunds();
        }

        origin.getBalance().decreaseAmount(transactiondto.getAmount());
        receiver.getBalance().increaseAmount(transactiondto.getAmount());

        if (origin instanceof Checking) {
            ((Checking) origin).chargePenaltyFee();
        } else if (origin instanceof Savings) {
            ((Savings) origin).chargePenaltyFee();
        }

        Transaction transaction = new Transaction(LocalDate.now(),new Money(transactiondto.getAmount()),origin,receiver);

        transactionRepository.save(transaction);
        accountRepository.save(origin);
        accountRepository.save(receiver);
    }

}
