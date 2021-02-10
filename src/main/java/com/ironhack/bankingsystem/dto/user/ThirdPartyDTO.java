package com.ironhack.bankingsystem.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ThirdPartyDTO {

    @NotEmpty
     private String hashedKey;
    @NotEmpty
    private String name;

    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(@NotEmpty String hashedKey, @NotNull Integer userId) {
        this.hashedKey = hashedKey;

    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
