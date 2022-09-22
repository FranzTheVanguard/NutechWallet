package com.fourrunstudios.nutechwallet.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fourrunstudios.nutechwallet.api.HistoryData;
import com.fourrunstudios.nutechwallet.databinding.HistoryItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private List<HistoryData> historyList;

    public RecyclerViewAdapter(List<HistoryData> historyList) {
        this.historyList = historyList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryItemBinding binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerViewAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.transactionType.setText(historyList.get(position).getTransaction_type());
        holder.binding.transactionId.setText("ID: "+historyList.get(position).getTransaction_id());
        holder.binding.transactionAmount.setText("Rp."+String.valueOf(historyList.get(position).getAmount()));
        holder.binding.transactionTime.setText(historyList.get(position).getTransaction_time());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        HistoryItemBinding binding;

        public MyViewHolder(@NonNull HistoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
