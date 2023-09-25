package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    ConstraintLayout exercicios, shorts;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        replaceFragment(new MenuFragment());
        Standard standard = new Standard();
        standard.actionColorDefault(this);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.menu_nav){
                replaceFragment(new MenuFragment());
            } else if (item.getItemId() == R.id.diario_nav) {
                replaceFragment(new DiarioFragment());
            } else if (item.getItemId() == R.id.insight_nav) {
                replaceFragment(new InsightFragment());
            } else if(item.getItemId() == R.id.exercicio_nav){
                replaceFragment(new ExercicioFragment());
            } else if(item.getItemId() == R.id.perfil_nav){
                replaceFragment(new PerfilFragment());
            }
           
            return true;
        });
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}