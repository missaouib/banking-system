package com.ironhack.bankingsystem.repository.accounts;

import com.ironhack.bankingsystem.model.account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {


}
