package com.ironhack.bankingsystem.dto.accounts;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransactionDTO {

    @NotNull
    private Integer originAccountId;
    @NotNull
    private Integer receiverAccountId;
    @NotNull
    @Min(value = 0,message = "Amount should grater than 0")
    private BigDecimal amount;
    private String nameOwner;

    public TransactionDTO(Integer originAccountId, Integer receiverAccountId, BigDecimal amount, String nameOwner) {
        this.originAccountId = originAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.nameOwner = nameOwner;
    }

    public Integer getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(Integer originAccountId) {
        this.originAccountId = originAccountId;
    }

    public Integer getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Integer receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }
}
