package com.example.stickherog;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PoisonReward extends reward2 implements renderable{
    private static final Image poisonImage;

    static {
        poisonImage = new Image("C:\\Users\\shaur\\Downloads\\poisonrewardnew.png");
    }
    public PoisonReward(double x, double y) {
        super(x, y);
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void render(GraphicsContext gc, double cameraOffset) {
        gc.drawImage(poisonImage, getX() - cameraOffset, getY(),20,20);
    }
}
