package com.example.androidhome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {
    private static final String NUMBER = "num";
    private static final String COLOR = "color";
    private int number;
    private int color;

    public NumberFragment() {
        super();
    }

    public static NumberFragment newInstance(NumberItem item) {
        NumberFragment fragment = new NumberFragment();
        Bundle args = new Bundle();
        args.putInt(NUMBER, item.num);
        args.putInt(COLOR, item.color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = getArguments().getInt(NUMBER);
            color = getArguments().getInt(COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number, container, false);
        TextView textView = view.findViewById(R.id.number_frag);
        textView.setText(String.valueOf(number));
        textView.setTextColor(color);
        return view;
    }
}

class NumberItem {
    NumberItem(int _num, int _color) {
        num = _num;
        color = _color;
    }
    int num;
    int color;
}