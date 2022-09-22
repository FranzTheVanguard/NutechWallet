package com.fourrunstudios.nutechwallet.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.databinding.ActivityTransferBinding;
import com.fourrunstudios.nutechwallet.fragment.ConfirmationDialog;
import com.fourrunstudios.nutechwallet.viewmodels.TransferActivityViewModel;

public class TransferActivity extends AppCompatActivity implements ConfirmationDialog.ConfirmationDialogListener {
    private ActivityTransferBinding binding;
    private TransferActivityViewModel viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transfer);
        viewModelSetup();
        binding.transferButton.setOnClickListener(view -> {
            openConfirmationDialog();
        });
    }

    private void openConfirmationDialog() {
        ConfirmationDialog dialog = new ConfirmationDialog();
        dialog.show(getSupportFragmentManager(), "Confirmation Dialog");
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.getLiveBalance().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.transferBalance.setText("Balance : "+integer);
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

    @Override
    public void confirm() {
        if(!viewmodel.tryTransfer(Integer.parseInt(binding.transferEdittext.getText().toString()))){
            Toast.makeText(this, "You don't have enough balance!", Toast.LENGTH_SHORT).show();
        }
    }

}