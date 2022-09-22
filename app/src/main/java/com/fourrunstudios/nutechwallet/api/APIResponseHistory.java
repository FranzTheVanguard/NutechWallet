package com.fourrunstudios.nutechwallet.api;

import java.util.List;

public class APIResponseHistory {
    private int status;
    private String message;
    private List<HistoryData> data;

    public APIResponseHistory(int status, String message, List<HistoryData> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<HistoryData> getData() {
        return data;
    }
}
