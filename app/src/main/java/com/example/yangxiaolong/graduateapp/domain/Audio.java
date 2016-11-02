package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/2.
 */
public class Audio {
    private String audio;
    private int duration;

    public Audio(String audio, int duration) {
        this.audio = audio;
        this.duration = duration;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "audio='" + audio + '\'' +
                ", duration=" + duration +
                '}';
    }
}
