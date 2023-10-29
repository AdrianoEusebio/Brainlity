package com.example.brainlity.Exercicios;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MeditacaoFragment extends Fragment {

    View view;
    TextView textView;
    ScrollView scrollView;
    Standard standard;
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meditacao, container, false);
        standard = new Standard();
        standard.actionColorDefault((AppCompatActivity) getActivity());

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtubeplayer);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = "QNNIwO25mhQ"; // Substitua pelo ID do vídeo do YouTube
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        textView = view.findViewById(R.id.text_link);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(v -> {
            String url = "https://blog.psicologiaviva.com.br/o-que-e-meditacao/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        });

        scrollView = view.findViewById(R.id.scrollView1);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0,0);
            }
        });
        return view;
    }
}