package com.fourrunstudios.nutechwallet.viewmodels;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.nutechwallet.repos.NutechRepo;

public class RegisterActivityViewModel extends ViewModel implements EmailPassValidator{
    private String email;
    private String password;
    private String repassword;
    private String first_name;
    private String last_name;
    private NutechRepo repo;
    public void init(){
        email=null;
        password=null;
        repassword=null;
        first_name=null;
        last_name=null;
        repo  = NutechRepo.getInstance();
    }

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
    public String getRepassword() {
        return repassword;
    }
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean validateReg(Context context){
        if(email == null || password == null || repassword == null || first_name == null || last_name == null){
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
        else if(!password.equals(repassword)){
            Toast.makeText(context, "Passwords must match!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!validateName()){
            Toast.makeText(context, "Names must not contain special characters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    public boolean validateEmail(){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public boolean validatePassword(){
        return password.matches("[a-zA-Z0-9]*") && password.length()>=6;
    }
    public boolean validateName(){
        return first_name.matches("[a-zA-Z]*") && last_name.matches("[a-zA-Z]*");
    }

    public void register(){
        repo.register(email, password, first_name, last_name);
    }
    public LiveData<Boolean> getRegistered(){
        return repo.getRegistered();
    }

    public LiveData<String> getMessage(){
        return repo.getMessage();
    }
}
