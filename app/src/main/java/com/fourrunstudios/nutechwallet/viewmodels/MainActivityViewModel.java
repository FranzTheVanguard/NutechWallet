package com.fourrunstudios.nutechwallet.viewmodels;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.nutechwallet.repos.NutechRepo;

public class MainActivityViewModel extends ViewModel implements EmailPassValidator{
    private String email;
    private String password;
    private NutechRepo repo;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void init() {
        email=null;
        password=null;
        repo  = NutechRepo.getInstance();
    }

    @Override
    public boolean validateEmail(){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public boolean validatePassword(){
        return password.matches("[a-zA-Z0-9]*") && password.length()>=6;
    }
    public boolean validateLogin(Context context){
        if(email == null || password == null){
            Toast.makeText(context, "Fields must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!validateEmail()){
            Toast.makeText(context, "Email must be in the correct format!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!validatePassword()){
            Toast.makeText(context, "Password must be at least 6 characters and contains no special characters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void login(){
        repo.login(email, password);
    }
    public LiveData<String> getLiveToken(){
        return repo.getLiveToken();
    }
    public LiveData<String> getMessage(){
        return repo.getMessage();
    }
    public void resetToken(){
        repo.resetToken();
    }
}
