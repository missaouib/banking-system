package com.ironhack.bankingsystem.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ThirdPartyDTO {

    @NotEmpty
     private String hashedKey;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(@NotEmpty String hashedKey, @NotEmpty String username, @NotEmpty String password) {
        this.hashedKey = hashedKey;
        this.username = username;
        this.password = password;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
