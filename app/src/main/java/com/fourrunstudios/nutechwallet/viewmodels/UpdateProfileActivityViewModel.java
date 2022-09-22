package com.fourrunstudios.nutechwallet.viewmodels;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.nutechwallet.api.NutechData;
import com.fourrunstudios.nutechwallet.repos.NutechRepo;

public class UpdateProfileActivityViewModel extends ViewModel {
    private NutechRepo repo = NutechRepo.getInstance();
    public LiveData<NutechData> getData(){
        return repo.getProfile();
    }
    public void updateProfile(String firstname, String lastname){
        repo.updateProfile(firstname, lastname);
    }
    public void init() {

    }
    public String validateNames(String firstname, String lastname){
        if(firstname.equals("") ||  lastname.equals("")){
            return "empty";
        }
        else if(!firstname.matches("[a-zA-Z]*") && !lastname.matches("[a-zA-Z]*")){
            return "special";
        }
        return "";
    }
}
