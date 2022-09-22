package com.fourrunstudios.nutechwallet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.nutechwallet.repos.NutechRepo;

public class TransferActivityViewModel extends ViewModel {
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
    public boolean tryTransfer(int amount){
        if(balance>=amount){
            repo.transfer(amount);
            return true;
        }
        else return false;
    }
    public LiveData<Integer> getLiveBalance(){
        return repo.getLiveBalance();
    }
}
