package com.fourrunstudios.nutechwallet.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fourrunstudios.nutechwallet.api.NutechData;

public interface NutechRepository {

    LiveData<Boolean> register(String email, String password, String first_name, String last_name);

    LiveData<String> login(String email, String password);

    void updateProfile(String token, String first_name, String last_name);

    NutechData getProfile(String token);

    void topUp(String token, int amount);

    void transfer(String token, int amount);

    NutechData getHistory(String token);

    int getBalance(String token);



    /**
     * Validators regarding the inputs of Repositories.
     * Login tests email and password.
     * Register tests names.
     * Profile tests names.
     * TopUp tests amounts.
     * Transfer tests amounts.
     */
    boolean validateLogin(String email, String password);
    boolean validateRegister(String email, String password, String first_name, String last_name);
    boolean validateProfile(String token, String first_name, String last_name);
    boolean validateTopUp(String token);
    boolean validateTransfer(String token);

}
