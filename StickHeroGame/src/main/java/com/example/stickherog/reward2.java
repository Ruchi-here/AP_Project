package com.example.stickherog;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class reward2 implements renderable {
    public double x;
    public double y;

    public reward2(double x, double y) {
        this.x = x;
        this.y = y;
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
    public void render(GraphicsContext gc,double cameraOffset) {
        gc.setFill(Color.YELLOW);
        gc.fillOval(this.x-cameraOffset, this.y, 10.0, 10.0);
    }

}

