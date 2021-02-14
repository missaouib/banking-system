package com.ironhack.bankingsystem.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdPartyDTO that = (ThirdPartyDTO) o;
        return Objects.equals(hashedKey, that.hashedKey) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashedKey, username, password);
    }
}
