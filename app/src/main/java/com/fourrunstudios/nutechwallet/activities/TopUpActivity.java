package com.fourrunstudios.nutechwallet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.databinding.ActivityTopUpBinding;
import com.fourrunstudios.nutechwallet.databinding.ActivityTransferBinding;
import com.fourrunstudios.nutechwallet.viewmodels.TopUpActivityViewModel;
import com.fourrunstudios.nutechwallet.viewmodels.TransferActivityViewModel;

public class TopUpActivity extends AppCompatActivity {
    private ActivityTopUpBinding binding;
    private TopUpActivityViewModel viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_up);
        viewModelSetup();
        binding.topupButton.setOnClickListener(view -> {
            viewmodel.topUp(Integer.parseInt(binding.topupEdittext.getText().toString()));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.getLiveBalance().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.topupBalance.setText(String.valueOf(integer));
                viewmodel.setBalance(integer);
            }
        });

    }
    private void viewModelSetup() {
        if(viewmodel == null){
            viewmodel = new ViewModelProvider(this).get(TopUpActivityViewModel.class);
        }
        binding.setViewmodel(viewmodel);
        viewmodel.init();
    }
}