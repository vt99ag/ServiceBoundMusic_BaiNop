package com.example.serviceboundmusic;

public class Music {
    private String name;
    private int mp3;

    public Music(String name, int mp3) {
        this.name = name;
        this.mp3 = mp3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMp3() {
        return mp3;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }
}
