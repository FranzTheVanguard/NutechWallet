package com.fourrunstudios.nutechwallet.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.api.NutechData;
import com.fourrunstudios.nutechwallet.databinding.ActivityUpdateProfileBinding;
import com.fourrunstudios.nutechwallet.viewmodels.UpdateProfileActivityViewModel;

public class UpdateProfileActivity extends AppCompatActivity {
    private ActivityUpdateProfileBinding binding;
    private UpdateProfileActivityViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);
        viewModelSetup();
        binding.updateButton.setOnClickListener(view -> {
            if(validateName()) viewmodel.updateProfile(binding.updateFirstname.getText().toString(), binding.updateLastname.getText().toString());
            });
    }
    private void viewModelSetup() {
        if(viewmodel == null){
            viewmodel = new ViewModelProvider(this).get(UpdateProfileActivityViewModel.class);
        }
        binding.setViewmodel(viewmodel);
        viewmodel.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.getData().observe(this, new Observer<NutechData>() {
            @Override
            public void onChanged(NutechData nutechData) {
                if(nutechData!=null) binding.updateNameTextview.setText(nutechData.getFirst_name()+" "+nutechData.getLast_name());
            }
        });
    }
    private boolean validateName(){
        switch(viewmodel.validateNames(binding.updateFirstname.getText().toString(), binding.updateLastname.getText().toString())){
            case "empty":
                Toast.makeText(this, "Fields must not be empty!", Toast.LENGTH_SHORT).show();
                return false;
            case "special":
                Toast.makeText(this, "Names must not contain special characters!", Toast.LENGTH_SHORT).show();
                return false;
            default:
                return true;
        }
    }

}