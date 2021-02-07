package com.ironhack.bankingsystem.model.user;

import com.ironhack.bankingsystem.model.account.Account;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{

    private LocalDate dateOfBirth;
    @Embedded
    private Address address;
    private String mailingAddress;
    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryOwnerAccounts;
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondOwnerAccounts;



    public AccountHolder() {
    }

    public AccountHolder(LocalDate dateOfBirth, Address address, String mailingAddress) {
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String name, LocalDate dateOfBirth, Address address, String mailingAddress) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
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

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
