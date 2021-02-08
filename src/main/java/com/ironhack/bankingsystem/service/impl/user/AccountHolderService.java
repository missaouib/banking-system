package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.exceptions.InsufficientFunds;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    public void transferMoney(TransactionDTO transferdto) {

        Account origin = accountRepository.findById(transferdto.getOriginAccountId()).orElseThrow(NoPresentAccount::new);
        Account receiver = accountRepository.findById(transferdto.getReceiverAccountId()).orElseThrow(NoPresentAccount::new);

        receiver.isOwnedBy(transferdto.getNameOwner());

        if (origin.getBalance().getAmount().compareTo(transferdto.getAmount()) < 0) {
            throw new InsufficientFunds();
        }

        origin.getBalance().decreaseAmount(transferdto.getAmount());
        receiver.getBalance().increaseAmount(transferdto.getAmount());

        if (origin instanceof Checking) {
            ((Checking) origin).chargePenaltyFee();
        } else if (origin instanceof Savings) {
            ((Savings) origin).chargePenaltyFee();
        }

        accountRepository.save(origin);
        accountRepository.save(receiver);
    }

}
