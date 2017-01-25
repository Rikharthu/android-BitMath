package com.example.uberv.bitmath;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class ConverterFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    @BindView(R.id.fragment_converter_first_number_et)
    EditText mFirstNumberEt;
    @BindView(R.id.fragment_converter_second_number_et)
    EditText mSecondNumberEt;
    @BindView(R.id.fragment_converter_first_number_radix_picker_np)
    NumberPicker mFirstNumberRadixNp;


    private int mPage;

    public static ConverterFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ConverterFragment fragment = new ConverterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);

        ButterKnife.bind(this,view);

        mFirstNumberRadixNp.setMinValue(2);
        mFirstNumberRadixNp.setMaxValue(32);

        return view;
    }

    @OnTextChanged(R.id.fragment_converter_first_number_et)
    void onFirstEtTextChanged(CharSequence text){
        int radix = mFirstNumberRadixNp.getValue();
        try{
            String binary = new BigInteger(text.toString(),radix).toString(10);
            mSecondNumberEt.setText(binary);
        }catch(Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnTextChanged(R.id.fragment_converter_second_number_et)
    void onSecondEtTextChanged(CharSequence text){

    }

}
