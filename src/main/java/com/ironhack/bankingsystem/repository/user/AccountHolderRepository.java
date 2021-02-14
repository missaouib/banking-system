package com.ironhack.bankingsystem.repository.user;


import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder,Integer> {

    public Optional<AccountHolder> findByUsername(String username);

}
