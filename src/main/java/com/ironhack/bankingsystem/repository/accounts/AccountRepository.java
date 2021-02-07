package com.ironhack.bankingsystem.repository.accounts;

import com.ironhack.bankingsystem.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
