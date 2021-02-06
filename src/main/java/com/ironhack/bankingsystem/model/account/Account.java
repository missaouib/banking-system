package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import java.math.BigDecimal;

public abstract class Account {

   private Money balance;
   private AccountHolder primaryOwner;
   private AccountHolder secondaryOwner;
   private BigDecimal penaltyFee;
   private Status status;



}
