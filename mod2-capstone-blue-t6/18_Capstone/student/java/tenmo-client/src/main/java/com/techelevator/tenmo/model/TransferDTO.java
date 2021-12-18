package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.security.Principal;

public class TransferDTO {
    private int userIdFrom;
    private int userIdTo;
    private BigDecimal amount;


    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public int getUserIdTo() {
        return userIdTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferDTO(int userIdFrom, int userIdTo, BigDecimal amount) {
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
        this.amount = amount;

    }
    //configure backend to receive this DTO
}
