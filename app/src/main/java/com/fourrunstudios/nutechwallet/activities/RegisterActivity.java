package com.fourrunstudios.nutechwallet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.databinding.ActivityRegisterBinding;
import com.fourrunstudios.nutechwallet.viewmodels.MainActivityViewModel;
import com.fourrunstudios.nutechwallet.viewmodels.RegisterActivityViewModel;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterActivityViewModel viewmodel;
    private RegisterActivity activity = this;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModelSetup();
        binding.reg.setOnClickListener(view -> {
            register();
        });
        binding.back.setOnClickListener(view -> onBackPressed());

    }
    private void viewModelSetup(){
        if(viewmodel == null){
            viewmodel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);
        }
        binding.setViewmodel(viewmodel);
        viewmodel.init();
    }
    private void register(){
        if(viewmodel.validateReg(this)){
            Log.d("Register"+counter, "");
            viewmodel.register();
            counter++;
            viewmodel.getRegistered().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    Log.d("register "+counter, "registered changed! "+aBoolean);
                    viewmodel.getRegistered().removeObservers(activity);
                    onBackPressed();
                }
            });
            viewmodel.getMessage().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Log.d("register "+counter, "message updated in register "+s);
                    Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
                    viewmodel.getMessage().removeObservers(activity);
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewmodel.getMessage().removeObservers(this);
    }
}