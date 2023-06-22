package ar.edu.ips.aus.seminario2.tetrominos.infra.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ar.edu.ips.aus.seminario2.tetrominos.R;
import ar.edu.ips.aus.seminario2.tetrominos.adapter.ScoreViewModel;
import ar.edu.ips.aus.seminario2.tetrominos.infra.ScoreAdapter;

public class HallOfFameActivity extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hall_of_fame);

        /** Iniciamos nuestro sonido de hall of fame */
        initMediaPlayer();

        RecyclerView highestScoresListView = findViewById(R.id.highest_scores);
        highestScoresListView.setLayoutManager(new LinearLayoutManager(this));

        ScoreViewModel model = new ViewModelProvider(this).get(ScoreViewModel.class);
        ScoreAdapter scoreAdapter = new ScoreAdapter(this, model);
        highestScoresListView.setAdapter(scoreAdapter);

        /** Declaración de listeners y botones correspondientes */
        Button restartButton = (Button) findViewById(R.id.buttonRestart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HallOfFameActivity.this,GameMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button exitButton = (Button) findViewById(R.id.buttonExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /** inicia el media player */
    private void initMediaPlayer(){
        mp = MediaPlayer.create(this, R.raw.halloffame);
        mp.setLooping(false);
        mp.setVolume(100,100);
        mp.start();
    }
}