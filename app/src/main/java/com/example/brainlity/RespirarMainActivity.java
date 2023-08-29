package com.example.brainlity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class RespirarMainActivity extends AppCompatActivity {


    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;
    private Button comecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirar_main);

        Standard standard = new Standard();
        standard.actionColorDefault(this);

        minutesPicker = findViewById(R.id.minutesPicker);
        secondsPicker = findViewById(R.id.secondsPicker);
        comecar = findViewById(R.id.button_comecar);
        TextView a = findViewById(R.id.text_help);


        configureMinutesPicker();
        configureSecondsPicker();
        configureComecar();
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
    private void configureComecar() {
        comecar.setOnClickListener(view -> {
                int selectedMinutes = minutesPicker.getValue();
                int selectedSeconds = secondsPicker.getValue() * 15;

                if(!(selectedMinutes == 0 && selectedSeconds == 0)){
                    Intent intent = new Intent(RespirarMainActivity.this, RespirarActivity.class);
                    intent.putExtra("selectedMinutes", selectedMinutes);
                    intent.putExtra("selectedSeconds", selectedSeconds);
                    startActivity(intent);
                } else {
                    Toast.makeText(RespirarMainActivity.this, "Colo alguma coisa ai namoral", Toast.LENGTH_SHORT).show();
                }
        });
    }
}