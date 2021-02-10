package com.ironhack.bankingsystem.model.user;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty extends User{

    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public ThirdParty(String userName, String password, String hashedKey) {
        super(userName, password);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
