package com.ironhack.bankingsystem.model.account;


import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {

    private String secretKey;


    public StudentChecking() {
    }

    public StudentChecking(String secretKey) {
        this.secretKey = secretKey;
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, Status.ACTIVE);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentChecking that = (StudentChecking) o;
        return secretKey.equals(that.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), secretKey);
    }
}
