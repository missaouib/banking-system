package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {


    private BigDecimal creditLimit;
    @Column(precision = 11,scale = 4)
    private BigDecimal interestRate;
    private LocalDate paidInterestRate;


    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner, Status.ACTIVE);
        setPaidInterestRate(getCreationDate());
        setCreditLimit(new BigDecimal(100));
        setInterestRate(new BigDecimal(0.2).setScale(1, RoundingMode.HALF_EVEN));
    }

    public void applyInterestRate() {
        Period period = Period.between(paidInterestRate, LocalDate.now());
        if (period.getMonths() >= 1) {
            getBalance().increaseAmount(getBalance().getAmount()
                    .multiply(interestRate
                            .divide(new BigDecimal(12),2,RoundingMode.HALF_EVEN)
                            .multiply(new BigDecimal(period.getMonths()))).setScale(2)
            );
            setPaidInterestRate(LocalDate.now());
        }
    }

    public LocalDate getPaidInterestRate() {
        return paidInterestRate;
    }

    public void setPaidInterestRate(LocalDate paidInterestRate) {
        this.paidInterestRate = paidInterestRate;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return creditLimit.equals(that.creditLimit) && interestRate.equals(that.interestRate) && paidInterestRate.equals(that.paidInterestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creditLimit, interestRate, paidInterestRate);
    }
}
