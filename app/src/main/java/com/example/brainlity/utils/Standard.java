package com.example.brainlity.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brainlity.R;

public class Standard {

    public boolean avaliarConexao(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }

    public  void actionColorDefault(AppCompatActivity appCompatActivity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = appCompatActivity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(appCompatActivity.getResources().getColor(R.color.background_item2)); // Substitua pela cor desejada
        }
    }

    public void vibrator(Context context){
        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            // Defina a duração da vibração em milissegundos (1000 ms = 1 segundo)
            long tempoDeVibracao = 1000; // 1 segundo
            // Faça o dispositivo vibrar
            vibrator.vibrate(tempoDeVibracao);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void toast(AppCompatActivity activity, String msg, int icon){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, activity.findViewById(R.id.custom_toast_container));

// Configurar o texto da mensagem personalizada
        TextView text = layout.findViewById(R.id.custom_toast_text);
        ImageView imageView = layout.findViewById(R.id.imageView8);
        switch (icon){
            case 1:
                layout.setBackgroundColor(activity.getResources().getColor(R.color.background_item5));
                imageView.setImageResource(R.drawable.baseline_check);
                break;
            case 2:
                layout.setBackgroundColor(activity.getResources().getColor(R.color.background_red));
                imageView.setImageResource(R.drawable.baseline_error);
                break;
            default:
                imageView.setImageResource(R.drawable.baseline_disabled_by_default);
        }
        text.setText(msg);

// Criar o Toast personalizado
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT); // Defina a duração do Toast
        toast.setView(layout); // Defina o layout personalizado

// Mostrar o Toast
        toast.show();
    }
}
