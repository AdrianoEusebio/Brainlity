package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class RespirarMainActivity extends AppCompatActivity {

    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirar_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.background_item1)); // Substitua pela cor desejada
        }

        minutesPicker = findViewById(R.id.minutesPicker);
        secondsPicker = findViewById(R.id.secondsPicker);

        configureMinutesPicker();
        configureSecondsPicker();


    }

    private void configureMinutesPicker() {
        minutesPicker.setOrientation(NumberPicker.VERTICAL);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(5);
        minutesPicker.setDisplayedValues(new String[]{"0", "1", "2", "3", "4", "5"});
        minutesPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            minutesPicker.setTextSize(40);
        }
    }

    private void configureSecondsPicker() {
        secondsPicker.setOrientation(NumberPicker.VERTICAL);
        secondsPicker.setMinValue(0);
        secondsPicker.setMaxValue(3);
        secondsPicker.setDisplayedValues(new String[]{"00", "15", "30", "45"});
        secondsPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            secondsPicker.setTextSize(40);
        }
    }
}