package com.example.stickherog;
public class Camera {
    private double x;

    public void tick(StickHeroCharacter player, double offset) {
        x = player.getX() - offset;
        if (x < 0) {
            x = 0;
        }
    }

    public double getX() {
        return x;
    }
}






