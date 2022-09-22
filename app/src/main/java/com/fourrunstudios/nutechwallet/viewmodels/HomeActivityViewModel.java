package com.fourrunstudios.nutechwallet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.nutechwallet.api.HistoryData;
import com.fourrunstudios.nutechwallet.api.NutechData;
import com.fourrunstudios.nutechwallet.repos.NutechRepo;

import java.util.List;

public class HomeActivityViewModel extends ViewModel {
    private NutechRepo repo = NutechRepo.getInstance();
    private int balance = 0;
    public void init() {

    }

    public LiveData<Integer> getLiveBalance(){
        return repo.getLiveBalance();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public LiveData<NutechData> getData(){
        return repo.getProfile();
    }

    public LiveData<List<HistoryData>> loadHistory() {
        return repo.getLiveHistory();
    }
    public LiveData<List<HistoryData>> getHistory() {
        return repo.getHistory();
    }
}
