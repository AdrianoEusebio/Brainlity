package com.example.brainlity.Exercicios.Respiração;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;

import java.lang.reflect.Field;

public class RespirarMainActivity extends AppCompatActivity {


    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;
    private ImageView back;
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
        back = findViewById(R.id.imageView6);

        configureMinutesPicker();
        configureSecondsPicker();
        configureComecar();

        back.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("ResourceAsColor")
    private void configureMinutesPicker() {
        minutesPicker.setOrientation(NumberPicker.VERTICAL);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(3);
        minutesPicker.setDisplayedValues(new String[]{"0", "1", "2", "3"});
        minutesPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            minutesPicker.setTextSize(40);
        }
        try {
            @SuppressLint("SoonBlockedPrivateApi") Field selectorWheelPaintField = NumberPicker.class.getDeclaredField("mSelectorWheelPaint");
            selectorWheelPaintField.setAccessible(true);
            ((Paint) selectorWheelPaintField.get(minutesPicker)).setColor(Color.RED);
            ((Paint) selectorWheelPaintField.get(minutesPicker)).setAntiAlias(true);
            minutesPicker.invalidate();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void configureSecondsPicker() {
        secondsPicker.setOrientation(NumberPicker.VERTICAL);
        secondsPicker.setMinValue(0);
        secondsPicker.setMaxValue(3);
        secondsPicker.setDisplayedValues(new String[]{"00", "15", "30","45"});
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
                    Toast.makeText(RespirarMainActivity.this, "Coloque uma contagem no cronometro", Toast.LENGTH_SHORT).show();
                }
        });
    }
}