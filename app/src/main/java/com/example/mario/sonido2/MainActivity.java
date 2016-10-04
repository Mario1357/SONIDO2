package com.example.mario.sonido2;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.SeekBar;
 
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer videos;
    AudioManager sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.kidlaugh);

        videos = MediaPlayer.create(this, R.raw.remember);

        sonido = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = sonido.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = sonido.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sonido.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
        });

        mPlayer.start();

        final SeekBar scrubber = (SeekBar) findViewById(R.id.progreso);
        scrubber.setMax(videos.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(videos.getCurrentPosition());
            }
        }, 0, 100);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                videos.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void empezar(View view) {
        videos.start();
    }

    public void pause(View view) {
        videos.pause();
    }
}