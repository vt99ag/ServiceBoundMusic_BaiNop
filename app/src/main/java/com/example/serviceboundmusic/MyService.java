package com.example.serviceboundmusic;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private MyPlayer myPlayer;
    private IBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ServiceDemo", "Đã gọi onCreate()");
        binder = new MyBinder(); // do MyBinder được extends Binder

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ServiceDemo", "Đã gọi onBind()");
        // trả về đối tượng binder cho ActivityMain
        return binder;

    }

    // Kết thúc một Service
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("ServiceDemo", "Đã gọi onBind()");
        myPlayer.stop();
        return super.onUnbind(intent);
    }

    // Xây dựng các phương thức thực hiện nhiệm vụ,
    // ở đây mình demo phương thức tua bài hát
    public void fastNextForward() {
        myPlayer.fastNextForward(); // tua đến giây thứ 120
    }

    public void fastPreForward() {

        myPlayer.fastPreForward(); // tua đến giây thứ 120
    }

    public void fastStart() {

        myPlayer.fastStart();
    }

    public void fastPause() {

        myPlayer.fastPause();
    }

    public class MyBinder extends Binder {

        // phương thức này trả về đối tượng MyService
        public MyService getService() {
            return MyService.this;
        }
    }

}

// Xây dựng một đối tượng riêng để chơi nhạc
class MyPlayer {
    // đối tượng này giúp phát một bài nhạc
    private MediaPlayer mediaPlayer;

    public MyPlayer() {
    }

    public MyPlayer(Context context, int mp3) {
        // Nạp bài nhạc vào mediaPlayer
        mediaPlayer = MediaPlayer.create(
                context, mp3);
        // Đặt chế độ phát lặp lại liên tục
        mediaPlayer.setLooping(true);
    }

    public void fastPreForward() {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int SUBTRACT_TIME = 5000;
        if (currentPosition - SUBTRACT_TIME > 0) {
            mediaPlayer.seekTo(currentPosition - SUBTRACT_TIME);
        }
    }

    public void fastNextForward() {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        int ADD_TIME = 5000;
        if (currentPosition + ADD_TIME < duration) {
            mediaPlayer.seekTo(currentPosition + ADD_TIME);
        }
    }

    public void fastPause() {
        mediaPlayer.pause();
    }

    public void fastStart() {
        mediaPlayer.start();
    }

    // phát nhạc
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    // dừng phát nhạc
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
