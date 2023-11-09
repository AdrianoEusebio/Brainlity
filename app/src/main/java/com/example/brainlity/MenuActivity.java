package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.brainlity.Utils.AsyncTask;
import com.example.brainlity.R;
import com.example.brainlity.Registro.DiarioFragment;
import com.example.brainlity.Exercicios.ExercicioFragment;
import com.example.brainlity.Insight.InsightFragment;
import com.example.brainlity.MenuFragment;
import com.example.brainlity.PerfilFragment;
import com.example.brainlity.Utils.Standard;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AsyncTask asyncTask = new AsyncTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Standard standard = new Standard();
        standard.actionColorDefault(this);

        if(standard.avaliarConexao(this)){
            asyncTask.execute();
        }

        replaceFragment(new MenuFragment());


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