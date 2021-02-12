package com.ironhack.bankingsystem.model.user;

import com.ironhack.bankingsystem.model.account.Account;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {

    private LocalDate dateOfBirth;


    @Embedded
    private Address address;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",column = @Column(name = "mailing_street")),
            @AttributeOverride(name = "postalCode",column = @Column(name = "mailing_postal_code")),
            @AttributeOverride(name = "city",column = @Column(name = "mailing_city")),
            @AttributeOverride(name = "country",column = @Column(name = "mailing_country"))})
    private Address mailingAddress;
    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryOwnerAccounts;
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondOwnerAccounts;


    public AccountHolder() {
    }

    public AccountHolder(String userName, String password, LocalDate dateOfBirth, Address address, Address mailingAddress) {
        super(userName, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }

    /** method to check the owner's age */

    public boolean isOlderThan24() {
        Period period = Period.between(dateOfBirth, LocalDate.now());
        int age = period.getYears();
        return age > 24;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getPrimaryOwnerAccounts() {
        return primaryOwnerAccounts;
    }

    public void setPrimaryOwnerAccounts(List<Account> primaryOwnerAccounts) {
        this.primaryOwnerAccounts = primaryOwnerAccounts;
    }

    public List<Account> getSecondOwnerAccounts() {
        return secondOwnerAccounts;
    }

    public void setSecondOwnerAccounts(List<Account> secondOwnerAccounts) {
        this.secondOwnerAccounts = secondOwnerAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHolder that = (AccountHolder) o;
        return dateOfBirth.equals(that.dateOfBirth) && address.equals(that.address) && mailingAddress.equals(that.mailingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, address, mailingAddress);
    }
}
