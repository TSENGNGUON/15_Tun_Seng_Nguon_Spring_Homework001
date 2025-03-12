package com.example.springhomework001;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class TicketUpateStatus {
    private int id = 0;
    private boolean paymentStatus;

    public TicketUpateStatus(int id,boolean paymentStatus){
        this.paymentStatus = paymentStatus;
        this.id = id;
    }

    public boolean getPaymentStatus() {
        return paymentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
