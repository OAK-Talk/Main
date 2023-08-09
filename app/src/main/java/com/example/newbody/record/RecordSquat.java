package com.example.newbody.record;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.newbody.Menu;
import com.example.newbody.R;
import com.example.newbody.Record;

public class RecordSquat extends AppCompatActivity {

    Button start, prev;
    long time;
    private VideoView mVideoView;
    private TextView timeInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_squat);

        prev = findViewById(R.id.prevButtonSquat);
        timeInput = findViewById(R.id.timeInput);

        start = findViewById(R.id.startSquat);
        Intent intent = getIntent();
        time = intent.getLongExtra("time", 0);

        timeInput.setText((time/60000)+"분");

        mVideoView = findViewById(R.id.squatEx);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/squat");
        mVideoView.setVideoURI(uri);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Record.class);
                startActivity(intent);
                finish();
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // 동영상 재생이 완료되면 다시 시작
                mVideoView.start();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordSquat.this, RecordSquatMain.class);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });
    }
}