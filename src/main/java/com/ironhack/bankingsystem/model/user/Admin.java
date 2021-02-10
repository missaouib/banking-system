package com.ironhack.bankingsystem.model.user;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

    public Admin() {
    }

    public Admin(String userName, String password) {
        super(userName, password);
    }
}
