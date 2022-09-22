package com.fourrunstudios.nutechwallet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.nutechwallet.repos.NutechRepo;

public class TopUpActivityViewModel extends ViewModel {
    private NutechRepo repo = NutechRepo.getInstance();
    private int balance = 0;
    private int amount = 0;
    public void init() {

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void topUp(){
        repo.topUp(amount);
    }
    public LiveData<Integer> getLiveBalance(){
        return repo.getLiveBalance();
    }

    public boolean validateAmount() {
        if(amount<10000){
            return false;
        }
        return true;
    }
}
