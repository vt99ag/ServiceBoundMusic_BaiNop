package com.example.serviceboundmusic;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_Play extends AppCompatActivity {

    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final TextView tvName = findViewById(R.id.tvName);

        final ImageView btNextFast = findViewById(R.id.btnNext);
        final ImageView btPreFast = findViewById(R.id.btnPrevios);
        final ImageView btPlay = findViewById(R.id.btnPlay);
        final ImageView btPause = findViewById(R.id.btnPause);

        Bundle intent = this.getIntent().getExtras();

        String name = intent.getString("name");
        int mp3 = intent.getInt("mp3");

        tvName.setText(name);

        MyPlayer myPlayer = new MyPlayer(this, mp3);
        myPlayer.play();


        btNextFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_Play.this, "Tua 5s", Toast.LENGTH_SHORT).show();

                myPlayer.fastNextForward();

            }
        });

        btPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(activity_Play.this, "Phát", Toast.LENGTH_SHORT).show();
                myPlayer.fastStart();

            }
        });

        btPreFast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(activity_Play.this, "Trở lại 5s", Toast.LENGTH_SHORT).show();
                myPlayer.fastPreForward();

            }
        });


        btPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // nếu service đang hoạt động
                Toast.makeText(activity_Play.this, "Dừng", Toast.LENGTH_SHORT).show();

                myPlayer.fastPause();
            }
        });
    }
}
