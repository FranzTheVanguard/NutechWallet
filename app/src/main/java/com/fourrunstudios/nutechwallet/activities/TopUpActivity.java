package com.fourrunstudios.nutechwallet.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.databinding.ActivityTopUpBinding;
import com.fourrunstudios.nutechwallet.fragment.ConfirmationDialog;
import com.fourrunstudios.nutechwallet.viewmodels.TopUpActivityViewModel;

public class TopUpActivity extends AppCompatActivity implements ConfirmationDialog.ConfirmationDialogListener, View.OnClickListener {
    private ActivityTopUpBinding binding;
    private TopUpActivityViewModel viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_up);
        viewModelSetup();
        setOnClick();
    }

    private void setOnClick() {
        binding.topup10k.setOnClickListener(this);
        binding.topup20k.setOnClickListener(this);
        binding.topup50k.setOnClickListener(this);
        binding.topup100k.setOnClickListener(this);
        binding.topup150k.setOnClickListener(this);
        binding.topup200k.setOnClickListener(this);
        binding.topup250k.setOnClickListener(this);
        binding.topup300k.setOnClickListener(this);
        binding.topup500k.setOnClickListener(this);
        binding.topupButton.setOnClickListener(this);
    }

    private void openConfirmationDialog() {
        ConfirmationDialog dialog = new ConfirmationDialog();
        dialog.show(getSupportFragmentManager(), "Confirmation Dialog");
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.getLiveBalance().observe(this, integer -> {
            binding.topupBalance.setText("Balance : "+integer);
            viewmodel.setBalance(integer);
        });

    }
    private void viewModelSetup() {
        if(viewmodel == null){
            viewmodel = new ViewModelProvider(this).get(TopUpActivityViewModel.class);
        }
        binding.setViewmodel(viewmodel);
        viewmodel.init();
    }
    public void topUp(){
        if(viewmodel.validateAmount()){
            viewmodel.topUp();
        }
        else Toast.makeText(this, "The minimum topup amount is 10000!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void confirm() {
        topUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.topup10k:
                viewmodel.setAmount(10000);
                break;
            case R.id.topup20k:
                viewmodel.setAmount(20000);
                break;
            case R.id.topup50k:
                viewmodel.setAmount(50000);
                break;
            case R.id.topup100k:
                viewmodel.setAmount(100000);
                break;
            case R.id.topup150k:
                viewmodel.setAmount(150000);
                break;
            case R.id.topup200k:
                viewmodel.setAmount(200000);
                break;
            case R.id.topup250k:
                viewmodel.setAmount(250000);
                break;
            case R.id.topup300k:
                viewmodel.setAmount(300000);
                break;
            case R.id.topup500k:
                viewmodel.setAmount(500000);
                break;
            default:
                viewmodel.setAmount(Integer.parseInt(binding.topupEdittext.getText().toString()));
        }
        openConfirmationDialog();
    }
}