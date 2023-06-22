package ar.edu.ips.aus.seminario2.tetrominos.infra.ui;

import androidx.appcompat.app.AppCompatActivity;
import ar.edu.ips.aus.seminario2.tetrominos.R;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartupActivity extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        // Iniciamos nuestro soundtrack
        initMediaPlayer();

        Button easyButton = (Button) findViewById(R.id.buttonEasy);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartupActivity.this,GameMainActivity.class);
                intent.putExtra("nivel",1);
                startActivity(intent);
                stopMediaPlayer();
                StartupActivity.this.finish(); //Destruimos la StartupActivity
            }
        });

        Button normalButton = (Button) findViewById(R.id.buttonNormal);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartupActivity.this,GameMainActivity.class);
                intent.putExtra("nivel",3);
                startActivity(intent);
                stopMediaPlayer();
                StartupActivity.this.finish();
            }
        });

        Button hardButton = (Button) findViewById(R.id.buttonHard);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartupActivity.this,GameMainActivity.class);
                intent.putExtra("nivel",6);
                startActivity(intent);
                stopMediaPlayer();
                StartupActivity.this.finish();
            }
        });

    }

    // inicia el media player
    private void initMediaPlayer(){
        mp = MediaPlayer.create(this, R.raw.startupsong);
        mp.setLooping(true);
        mp.setVolume(100,100);
        mp.start();
    }

    // stop del media player
    private void stopMediaPlayer(){
        mp.release();
    }
}