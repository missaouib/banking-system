package com.ironhack.bankingsystem.repository.accounts;

import com.ironhack.bankingsystem.model.account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings,Integer> {


}
