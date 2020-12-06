package com.example.serviceboundmusic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private MusicAdapter mAdapter;
    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btOn = findViewById(R.id.btOn);
        final Button btOff = findViewById(R.id.btOff);
        final ImageView btPlay = findViewById(R.id.btnPlay);
        mRecyclerView = findViewById(R.id.recyclerView);

        Music music1 = new Music("Black Jackz", R.raw.blackjackz);
        Music music2 = new Music("Buon Vuong Mau Ao", R.raw.buonvuongmauao_nguyenhung);
        Music music3 = new Music("Chang The Tim Duoc Em", R.raw.changthetimduocem);
        Music music4 = new Music("Gap Nhung Khong O Lai", R.raw.gapnhungkolai);
        Music music5 = new Music("Hanh Phuc Bo Em Roi", R.raw.hanhphucboeroi);
        Music music6 = new Music("Sao Em Lai Tat May", R.raw.saoemlaitatmay);

        ArrayList<Music> arrayList = new ArrayList<>();
        arrayList.add(music1);
        arrayList.add(music2);
        arrayList.add(music3);
        arrayList.add(music4);
        arrayList.add(music5);
        arrayList.add(music6);


        // Khởi tạo ServiceConnection

        connection = new ServiceConnection() {

            // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
            @Override
            public void onServiceDisconnected(ComponentName name) {

                isBound = false;
            }

            // Phương thức này được hệ thống gọi khi kết nối tới service thành công
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder binder = (MyService.MyBinder) service;
                myService = binder.getService(); // lấy đối tượng MyService
                isBound = true;

            }
        };

        // Khởi tạo intent
        final Intent intent =
                new Intent(MainActivity.this,
                        MyService.class);

        btOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bắt đầu một service sủ dụng bind
                bindService(intent, connection,
                        Context.BIND_AUTO_CREATE);
                mAdapter = new MusicAdapter(arrayList, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                Toast.makeText(MainActivity.this, "Bật Service", Toast.LENGTH_SHORT).show();

                // Đối thứ ba báo rằng Service sẽ tự động khởi tạo
            }
        });

        btOff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Nếu Service đang hoạt động
                if (isBound) {
                    // Tắt Service
                    unbindService(connection);
                    isBound = false;
                    Toast.makeText(MainActivity.this, "Tắt Service", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}