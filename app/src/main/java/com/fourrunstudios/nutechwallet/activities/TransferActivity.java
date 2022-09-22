package com.fourrunstudios.nutechwallet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.databinding.ActivityHomeBinding;
import com.fourrunstudios.nutechwallet.databinding.ActivityTransferBinding;
import com.fourrunstudios.nutechwallet.databinding.ActivityTransferBindingImpl;
import com.fourrunstudios.nutechwallet.viewmodels.HomeActivityViewModel;
import com.fourrunstudios.nutechwallet.viewmodels.TransferActivityViewModel;

public class TransferActivity extends AppCompatActivity {
    private ActivityTransferBinding binding;
    private TransferActivityViewModel viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transfer);
        viewModelSetup();
        binding.transferButton.setOnClickListener(view -> {
            if(!viewmodel.tryTransfer(Integer.parseInt(binding.transferEdittext.getText().toString()))){
                Toast.makeText(this, "You don't have enough balance!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.getLiveBalance().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("Balance in", "onChanged: updated to "+integer);
                binding.transferBalance.setText(String.valueOf(integer));
                viewmodel.setBalance(integer);
            }
        });

    }

    private void viewModelSetup() {
        if(viewmodel == null){
            viewmodel = new ViewModelProvider(this).get(TransferActivityViewModel.class);
        }
        binding.setViewmodel(viewmodel);
        viewmodel.init();
    }
}