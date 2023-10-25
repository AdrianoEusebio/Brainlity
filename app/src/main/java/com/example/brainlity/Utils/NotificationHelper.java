package com.example.brainlity.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.brainlity.R;

public class NotificationHelper {

    private final String CHANNEL_ID = "CHANNEL_BRAINLITY"; // Identificador do canal de notificação (deve ser único)

    public void showNotification(Context context, String title, String content) {
        createNotificationChannel(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_check) // Ícone da notificação
                .setContentTitle(title) // Título da notificação
                .setContentText(content) // Conteúdo da notificação
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Brainlity";
            String description = "Canal de notificação";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
