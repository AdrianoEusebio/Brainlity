package com.example.brainlity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

public class MenuFragment extends Fragment {
    private TextView textUser;
    private View view;
    private SharedPreferences sharedPreferences;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FraseDiaria");
    private TextView fraseDiaria;
    private Standard standard;
    private ImageView imageShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        sharedPreferences = getActivity().getSharedPreferences("Usuario", getContext().MODE_PRIVATE);
        String nome = sharedPreferences.getString("nome", "");
        textUser = view.findViewById(R.id.textUser);
        textUser.setText("Olá, " + nome);
        imageShare = view.findViewById(R.id.image_share);
        standard = new Standard();
        fraseDiaria = view.findViewById(R.id.frase_diaria);
        inserirFraseDiaria();
        notificacao();

        imageShare.setOnClickListener(v -> {
            standard.shareViaWhatsApp(getContext(),fraseDiaria.getText().toString());
        });

        return view;
    }

    public void inserirFraseDiaria() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dado = snapshot.getValue(String.class);
                fraseDiaria.setText(dado);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                String dado = "ERROR";
                fraseDiaria.setText(dado);
            }
        });
    }

    public void notificacao() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    System.out.println("Fetching FCM registration token failed" + task.getException());
                    return;
                }
                // Get new FCM registration token
                String token = task.getResult();
                System.out.println("SEU TOKEN É: " + token);
            }
        });
    }
}