package com.ironhack.bankingsystem.repository.accounts;

import com.ironhack.bankingsystem.model.account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking,Integer> {
}
