package com.example.androidhome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NumberListFragment extends Fragment implements View.OnClickListener {
    private NumberItemAdapter adapter;
    private List<NumberItem> numbers = new ArrayList<>();
    private static final String ELEMS_NUM_KEY = "ELEMS_NUM";
    private int numberSize;

    public NumberListFragment() {
        super();
    }

    static NumberListFragment newInstance() {
        return new NumberListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int elemsNum = getResources().getInteger(R.integer.elements_num);

        if (savedInstanceState != null) {
            elemsNum = savedInstanceState.getInt(ELEMS_NUM_KEY, elemsNum);
        }
        for (int i = 0; i < elemsNum; i++) {
            numbers.add(createItemToAdd());
            ++numberSize;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        NumberItemAdapter.NumberClicker listener = (NumberItemAdapter.NumberClicker) getActivity();
        adapter = new NumberItemAdapter(numbers, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.columns_num)));
        view.findViewById(R.id.button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter.ClearRefs();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        state.putInt(ELEMS_NUM_KEY, numberSize);
        super.onSaveInstanceState(state);
    }

    @Override
    public void onClick(View view) {
        addItem();
    }

    private void addItem() {
        NumberItem item = createItemToAdd();
        numbers.add(item);
        ++numberSize;
        adapter.notifyItemInserted(item.num - 1);
    }

    private NumberItem createItemToAdd() {
        int index = numbers.size() + 1;
        return new NumberItem(index, getResources().getColor(index % 2 == 0 ? R.color.evenNumber : R.color.oddNumber));
    }
}