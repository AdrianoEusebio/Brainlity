package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.brainlity.utils.Standard;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Standard standard = new Standard();
        standard.actionColorDefault(this);
    }
}