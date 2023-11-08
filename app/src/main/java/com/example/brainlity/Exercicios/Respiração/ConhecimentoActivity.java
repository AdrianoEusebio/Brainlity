package com.example.brainlity.Exercicios.Respiração;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.brainlity.Exercicios.AnsiedadeFragment;
import com.example.brainlity.Exercicios.ExercicioFragment;
import com.example.brainlity.Exercicios.FocoFragment;
import com.example.brainlity.Exercicios.MeditacaoFragment;
import com.example.brainlity.Exercicios.RotinaFragment;
import com.example.brainlity.Exercicios.SaudeMentalFragment;
import com.example.brainlity.Exercicios.SonoFragment;
import com.example.brainlity.Insight.InsightFragment;
import com.example.brainlity.MenuFragment;
import com.example.brainlity.PerfilFragment;
import com.example.brainlity.R;
import com.example.brainlity.Registro.DiarioFragment;

public class ConhecimentoActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conhecimento);
        fragmentSelect();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout2,fragment);
        fragmentTransaction.commit();
    }

    public void fragmentSelect(){
        SharedPreferences sharedPreferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        if(sharedPreferences.getString("conhecimento","").equals("meditacao")){
            replaceFragment(new MeditacaoFragment());
        } else if (sharedPreferences.getString("conhecimento","").equals("ansiedade")) {
            replaceFragment(new AnsiedadeFragment());
        } else if (sharedPreferences.getString("conhecimento","").equals("foco")) {
            replaceFragment(new FocoFragment());
        } else if(sharedPreferences.getString("conhecimento","").equals("sono")){
            replaceFragment(new SonoFragment());
        } else if(sharedPreferences.getString("conhecimento","").equals("saudemental")){
            replaceFragment(new SaudeMentalFragment());
        } else if(sharedPreferences.getString("conhecimento","").equals("rotina")){
            replaceFragment(new RotinaFragment());
        }
    }
}