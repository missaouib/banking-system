package com.ironhack.bankingsystem.repository.accounts;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    public List<Account> findByPrimaryOwnerOrSecondaryOwner(AccountHolder primaryOwnerId, AccountHolder secondaryOwnerId);
    public List<Account> findByStatus(Status status);


}
