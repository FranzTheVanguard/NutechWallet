package com.fourrunstudios.nutechwallet.api;

public class APIResponse {
    private int status;
    private String message;
    private NutechData data;

    public APIResponse(int status, String message, NutechData data) {
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
    public NutechData getData() {
        return data;
    }
}
