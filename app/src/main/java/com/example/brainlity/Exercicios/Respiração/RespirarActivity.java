package com.example.brainlity.Exercicios.Respiração;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.example.brainlity.Utils.Musica;

import pl.droidsonroids.gif.GifImageView;

public class RespirarActivity extends AppCompatActivity {

    //Definições dos atributos
    private TextView cronometro; // textView onde mostrará o tempo
    private ImageView rollback,volume; // imagenviews do sistema de musica
    private Button button; //botão começar
    private GifImageView gifImageView; //gifImage onde será setado o gif utilizado na ferramenta
    private CountDownTimer countDownTimer; //Variavel que irá fazer a contagem
    private static int selectedMinutes, selectedSeconds; // variaveis que receberá o minuto e o segundo da tela "RespirarMainActivity"
    private long totalMilliseconds; // a soma dos minutos e segundos
    private Standard standard = new Standard(); // objeto padrão da classe Standard

    private Musica musica; // varial da classe "Musica"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirar);

        //Criando os objetos das classes do package utils
        musica = new Musica(this,R.raw.reflected);
        standard.actionColorDefault(this);

        //Setando ID dos componentes da tela
        cronometro = findViewById(R.id.textView_cronometro);
        button = findViewById(R.id.button2);
        rollback = findViewById(R.id.image_rollback_respirar);
        volume = findViewById(R.id.imageView);
        gifImageView = findViewById(R.id.gifImageView);

        //Recebendo os valores da classe "RespirarActivityMain"
        selectedMinutes = getIntent().getIntExtra("selectedMinutes", 0);
        selectedSeconds = getIntent().getIntExtra("selectedSeconds", 0);

        //Somando os valores e convertendo para milisegundo
        totalMilliseconds = (selectedMinutes * 60 + selectedSeconds) * 1000;
        cronometro.setText(String.format("%02d:%02d",selectedMinutes,selectedSeconds));

        //ação de voltar do imageView rollback
        rollback.setOnClickListener(view ->{
            onBackPressed();
        });

        //ação do imageView Volume
        volume.setOnClickListener(view ->{
            volume();
        });

        //Ação do button Começar
        buttonClick();
    }


    //metodo padrão de back do celular
    @Override
    public void onBackPressed() {

        //Se a musica estiver tocando, ela vai parar quando esse metodo for usado
        if(musica.isPlaying()){
            musica.pauseMusic();
        }
        super.onBackPressed();
    }


    //metodo do button Começar
    public void buttonClick(){
        button.setOnClickListener(item ->{

            //verificação baseada do text do button
            if (button.getText().equals("Começar")) {

                //setando um texto no button
                button.setText("Resetar");

                //chamar o metodo do standard que faz o celular vibrar
                standard.vibrator(RespirarActivity.this);

                //setando o gif no gifImageView
                gifImageView.setImageResource(R.drawable.gif_ansiedade_respiracao);

                //chamando o metodo volume
                volume();

                //verificação da variavel countDownTimer
                if (countDownTimer == null) {

                    //variavel countDownTimer recebendo o metodo que faz a contagem que é do tipo CountDownTimer
                    countDownTimer = startCountdown(totalMilliseconds);
                }

                //startando o countDownTimer
                countDownTimer.start();
            } else {

                //setando um texto no button
                button.setText("Começar");

                //musica é pausada
                musica.pauseMusic();

                //gifImageView recebe uma imagem estatica do gif pausado
                gifImageView.setImageResource(R.drawable.gif_pause);

                //O TextView cronometro é resetado para o tempo inicial
                cronometro.setText(String.format("%02d:%02d",selectedMinutes,selectedSeconds));

                //verificação da variavel countDownTimer
                if (countDownTimer != null) {

                    //cancelar a contagem
                    countDownTimer.cancel();
                }
            }
        });
    }


    //Metodo de contagem do tempo setado. startCountdown vai receber o tempo total convertido em milisegundo
    private CountDownTimer startCountdown(long milliseconds) {

        //1000 = 1 segundo de intervalo entre a contagem
       return new CountDownTimer(milliseconds, 1000) {
            public void onTick(long millisUntilFinished) {

                //sistema do cronometro
                long totalSeconds = millisUntilFinished / 1000;
                long minutes = totalSeconds / 60;
                long seconds = totalSeconds % 60;

                //setando o texto referente ao segundo passado
                cronometro.setText(String.format("%02d:%02d", minutes, seconds));
            }

            //oq acontece quando a contagem acabar
            public void onFinish() {
                musica.pauseMusic();
                gifImageView.setImageResource(R.drawable.gif_pause);
                cronometro.setText("00:00");
            }
        }.start();
    }


    //metodo para desligar e ligar a musica
    public boolean volume(){

        //verificação se a musica esta tocando, caso esteja ela é pausada e trocará o imageView volume para uma imagem referente ao pause
            if(musica.isPlaying()){
                musica.pauseMusic();
                volume.setImageResource(R.drawable.baseline_volume_off);
                return true;
            }else {
                musica.startMusic();
                volume.setImageResource(R.drawable.baseline_volume_up);
                return false;
            }
    }
}