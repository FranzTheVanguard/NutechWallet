package com.fourrunstudios.nutechwallet.api;

public class HistoryData {
    private String transaction_id;
    private String transaction_time;
    private String transaction_type;

    public HistoryData(String transaction_id, String transaction_time, String transaction_type) {
        this.transaction_id = transaction_id;
        this.transaction_time = transaction_time;
        this.transaction_type = transaction_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public String getTransaction_type() {
        return transaction_type;
    }
}
