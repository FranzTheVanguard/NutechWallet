package com.fourrunstudios.nutechwallet.api;

public class NutechData {
    private String token;
    private Integer balance;
    private String first_name;
    private String last_name;

    public NutechData(String token, int balance, String first_name, String last_name) {
        this.token = token;
        this.balance = balance;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getToken() {
        return token;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
