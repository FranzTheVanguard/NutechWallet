package com.fourrunstudios.nutechwallet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.api.NutechData;
import com.fourrunstudios.nutechwallet.databinding.ActivityHomeBinding;
import com.fourrunstudios.nutechwallet.viewmodels.HomeActivityViewModel;
import com.fourrunstudios.nutechwallet.viewmodels.RegisterActivityViewModel;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private HomeActivityViewModel viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewModelSetup();
        binding.topupLayout.setOnClickListener(view -> {
            startActivity(new Intent(this, TopUpActivity.class));
        });
        binding.transferLayout.setOnClickListener(view -> {
            startActivity(new Intent(this, TransferActivity.class));
        });
        binding.updateProfile.setOnClickListener(view -> {
            startActivity(new Intent(this, UpdateProfileActivity.class));
        });
        binding.historyCardview.setOnClickListener(view -> {
            loadHistory();
        });
    }
    private void viewModelSetup() {
        if(viewmodel == null){
            viewmodel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        }
        binding.setViewmodel(viewmodel);
        viewmodel.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.getLiveBalance().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.balanceText.setText(String.valueOf(integer));
                viewmodel.setBalance(integer);
            }
        });
        viewmodel.getData().observe(this, new Observer<NutechData>() {
            @Override
            public void onChanged(NutechData nutechData) {
                if(nutechData!=null) binding.nameTextview.setText(nutechData.getFirst_name()+" "+nutechData.getLast_name());
            }
        });
    }
    private void loadHistory(){
        viewmodel.loadHistory();
    }
}