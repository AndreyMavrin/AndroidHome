package com.example.androidhome;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NumberItemAdapter extends RecyclerView.Adapter<NumberItemAdapter.NumberViewHolder> {

    private List<NumberItem> numbersList;
    private NumberClicker listener;

    public NumberItemAdapter(List<NumberItem> items, NumberClicker _listener) {
        numbersList = items;
        listener = _listener;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.number_in_list, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        final NumberItem number = numbersList.get(position);
        holder.view.setText(String.valueOf(number.num));
        holder.view.setTextColor(number.color);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNumberClicked(number);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return numbersList.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {
        final TextView view;

        NumberViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.number_small);
        }
    }

    void ClearRefs() {
        listener = null;
    }

    public interface NumberClicker {
        void onNumberClicked(NumberItem item);
    }
}