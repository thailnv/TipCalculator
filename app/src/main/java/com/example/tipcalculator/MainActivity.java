package com.example.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.EditText;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TextView amountTextView ;
    private TextView percentTextView;
    private TextView tipTextView;
    private EditText amountEditText;
    private TextView totalTextView;
    private double amount = 0.0;
    private double percent = 0.15;
    private double tip = 0.0;
    private double total = 0.0;
    private static final NumberFormat numberCurrency  = NumberFormat.getCurrencyInstance();
    private static final NumberFormat numberPercent = NumberFormat.getPercentInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView  = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        tipTextView.setText(numberCurrency.format(0));
        totalTextView.setText(numberCurrency.format(0));
        String percentStr = String.valueOf(15) + "%";
        percentTextView.setText(percentStr);
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditInput);
        final SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(percentSeekBarChange);
    }
    private void calc(){
        tip = amount * percent;
        total = amount + tip;
        tipTextView.setText(numberCurrency.format(tip));
        totalTextView.setText(numberCurrency.format(total));
    }

    private final TextWatcher amountEditInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            amountEditText.setTextSize(0.0f);
            try{
                amount = Double.parseDouble(s.toString())/100;
                
                amountTextView.setText(numberCurrency.format(amount));
            }
            catch(NumberFormatException e){
                amount = 0.0;
                amountTextView.setText("");
            }
            calc();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private final SeekBar.OnSeekBarChangeListener percentSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0;
            String percentStr = String.valueOf(progress) + "%";
            percentTextView.setText(percentStr);
            calc();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}