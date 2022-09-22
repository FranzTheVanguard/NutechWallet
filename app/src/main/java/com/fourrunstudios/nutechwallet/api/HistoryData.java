package com.fourrunstudios.nutechwallet.api;

import java.text.DecimalFormat;

public class HistoryData {
    private String transaction_id;
    private String transaction_time;
    private String transaction_type;
    private int amount;

    public HistoryData(String transaction_id, String transaction_time, String transaction_type, int amount) {
        this.transaction_id = transaction_id;
        this.transaction_time = transaction_time;
        this.transaction_type = transaction_type;
        this.amount = amount;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public String getTransaction_type() {
        return transaction_type.substring(0,1).toUpperCase() + transaction_type.substring(1).toLowerCase();
    }

    public String getAmount() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(amount);
    }
}
