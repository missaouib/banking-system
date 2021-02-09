package com.ironhack.bankingsystem.repository.accounts;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    public List<Account> findByStatus(Status status);


}
