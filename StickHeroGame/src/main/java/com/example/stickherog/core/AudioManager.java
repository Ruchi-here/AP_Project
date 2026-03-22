package com.example.stickherog.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private MediaPlayer bgm;

    public void playBackground() {
        try {
            Media media = new Media(getClass().getResource("/bg.mp3").toExternalForm());
            bgm = new MediaPlayer(media);
            bgm.setCycleCount(MediaPlayer.INDEFINITE);
            bgm.play();
        } catch (Exception e) {
            System.out.println("BGM error");
        }
    }

    public void playReward() {
        playOne("/coin.mp3");
    }

    public void playDead() {
        playOne("/dead.mp3");
    }

    private void playOne(String file) {
        try {
            Media media = new Media(getClass().getResource(file).toExternalForm());
            new MediaPlayer(media).play();
        } catch (Exception e) {
            System.out.println("Sound error");
        }
    }
}