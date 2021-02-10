package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.dto.user.ThirdPartyTransactionDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.*;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.user.ThirdParty;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import com.ironhack.bankingsystem.repository.user.ThirdPartyRepository;
import com.ironhack.bankingsystem.service.interfaces.user.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService implements IThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void sendMoney(ThirdPartyTransactionDTO thirdPartyTransactiondto, UserDetails user){

        ThirdParty thirdParty = thirdPartyRepository.findById(thirdPartyTransactiondto.getThirdPartyId()).orElseThrow(NoPresentThirdParty::new);
        if(!thirdParty.getUsername().equals(user.getUsername())){
            throw new UnauthorizedAccess();
        }

        if (!thirdPartyTransactiondto.getHashedKey().equals(thirdParty.getHashedKey())){
            throw new HashedKeyIncorrect();
        }

        Account account = accountRepository.findById(thirdPartyTransactiondto.getAccountId()).orElseThrow(NoPresentAccount::new);

        if (account.getStatus() == Status.FROZEN){
            throw new FrozenAccount();
        }
        account.getBalance().increaseAmount(thirdPartyTransactiondto.getAmount());

    }

    public void receiveMoney(ThirdPartyTransactionDTO thirdPartyTransactiondto, UserDetails user){
        Account account = accountRepository.findById(thirdPartyTransactiondto.getAccountId()).orElseThrow(NoPresentAccount::new);
        ThirdParty thirdParty = thirdPartyRepository.findById(thirdPartyTransactiondto.getThirdPartyId()).orElseThrow(NoPresentThirdParty::new);

        if(!thirdParty.getUsername().equals(user.getUsername())){
            throw new UnauthorizedAccess();
        }

        if (!thirdPartyTransactiondto.getHashedKey().equals(thirdParty.getHashedKey())){
            throw new HashedKeyIncorrect();
        }
        if (account.getStatus() == Status.FROZEN){
            throw new FrozenAccount();
        }
        if (account.getBalance().getAmount().compareTo(thirdPartyTransactiondto.getAmount()) < 0) {
            throw new InsufficientFunds();
        }

        account.getBalance().decreaseAmount(thirdPartyTransactiondto.getAmount());
        accountRepository.save(account);
    }


}
