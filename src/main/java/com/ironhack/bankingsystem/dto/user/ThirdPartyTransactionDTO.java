package com.ironhack.bankingsystem.dto.user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class ThirdPartyTransactionDTO {

    @NotNull
    private Integer thirdPartyId;
    private String hashedKey;
    @NotNull
    private Integer accountId;
    @NotNull
    @Min(value = 0,message = "must be greater than 0")
    private BigDecimal amount;
    @NotEmpty
    private String accountSecretKey;


    public ThirdPartyTransactionDTO() {
    }

    public ThirdPartyTransactionDTO(@NotNull Integer thirdPartyId, @NotEmpty String hashedKey, @NotNull Integer accountId, @NotNull @Min(value = 0, message = "must be greater than 0") BigDecimal amount, @NotNull String accountSecretKey) {
        this.thirdPartyId = thirdPartyId;
        this.hashedKey = hashedKey;
        this.accountId = accountId;
        this.amount = amount;
        this.accountSecretKey = accountSecretKey;
    }

    public Integer getThirdPartyId() {
        return thirdPartyId;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountSecretKey() {
        return accountSecretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdPartyTransactionDTO that = (ThirdPartyTransactionDTO) o;
        return Objects.equals(thirdPartyId, that.thirdPartyId) && Objects.equals(hashedKey, that.hashedKey) && Objects.equals(accountId, that.accountId) && Objects.equals(amount, that.amount) && Objects.equals(accountSecretKey, that.accountSecretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thirdPartyId, hashedKey, accountId, amount, accountSecretKey);
    }
}
