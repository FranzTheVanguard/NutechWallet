package com.fourrunstudios.nutechwallet.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fourrunstudios.nutechwallet.R;
import com.fourrunstudios.nutechwallet.adapter.RecyclerViewAdapter;
import com.fourrunstudios.nutechwallet.api.HistoryData;
import com.fourrunstudios.nutechwallet.api.NutechData;
import com.fourrunstudios.nutechwallet.databinding.ActivityHomeBinding;
import com.fourrunstudios.nutechwallet.viewmodels.HomeActivityViewModel;

import java.util.List;

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
        Glide.with(this)
                .load("https://i.imgur.com/3TV39Qm.png")
                .into(binding.transferImage);
        Glide.with(this)
                .load("https://i.imgur.com/acjPLq6.png")
                .into(binding.topupImage);
        Glide.with(this)
                .load("https://i.imgur.com/tbed3ES.png")
                .apply(new RequestOptions().override(60, 60))
                .into(binding.updateProfile);
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
        if(!viewmodel.loadHistory().hasActiveObservers()){
            viewmodel.getHistory().observe(this, new Observer<List<HistoryData>>() {
                @Override
                public void onChanged(List<HistoryData> historyData) {
                    setupAdapter(historyData);
                }
            });
        }
    }

    private void setupAdapter(List<HistoryData> historyList) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(historyList);
        binding.historyView.setAdapter(adapter);
        if(binding.historyView.getLayoutManager()==null) binding.historyView.setLayoutManager(new LinearLayoutManager(this));
    }
}