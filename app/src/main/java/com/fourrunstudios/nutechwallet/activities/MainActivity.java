package com.fourrunstudios.nutechwallet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.databinding.ActivityMainBinding;
import com.fourrunstudios.nutechwallet.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewmodel;
    private int counter = 0;
    private MainActivity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModelSetup();
        binding.gotoReg.setOnClickListener(view -> {
            goToRegister();
        });
        binding.submit.setOnClickListener(view -> {
            Log.d("Submit Button ", view.toString());
            tryLogin();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.resetToken();
    }

    private void viewModelSetup(){
        if(viewmodel == null){
            viewmodel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        }
        binding.setViewmodel(viewmodel);
        viewmodel.init();
    }

    public void tryLogin(){
        if(viewmodel.validateLogin(this)){
            Log.d("Login"+counter, "");
            viewmodel.login();
            counter++;
            viewmodel.getLiveToken().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Log.d("Login "+counter, "Token update, go to activity "+s);
                    viewmodel.getLiveToken().removeObservers(activity);
                    startActivity(new Intent(activity, HomeActivity.class));
                }
            });
            viewmodel.getMessage().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Log.d("Login"+counter, "message changed in main "+s);
                    Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
                    viewmodel.getMessage().removeObservers(activity);
                }
            });
        }
    }
    public void goToRegister(){
        startActivity(new Intent(this, RegisterActivity.class));
    }
    @Override
    protected void onStop() {
        super.onStop();
        viewmodel.getMessage().removeObservers(this);

    }
}