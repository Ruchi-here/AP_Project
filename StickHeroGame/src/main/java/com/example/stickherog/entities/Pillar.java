package com.example.stickherog;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pillar extends Platform {
   public Pillar(double x, double y, double width, double height) {
       super(x, y, width, height);
   }

   @Override
   public void render(GraphicsContext gc) {
       gc.setFill(Color.BROWN);
       gc.fillRect(getX(), getY(), getWidth(), getHeight());
   }
}
