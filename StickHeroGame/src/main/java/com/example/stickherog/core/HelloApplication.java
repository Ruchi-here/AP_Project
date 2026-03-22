package com.example.stickherog.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.example.stickherog.engine.GameEngine;
import com.example.stickherog.input.InputHandler;

public class GameApplication extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private GameEngine engine;

    public int getFrameWidth() {
        return WIDTH;
    }

    public int getFrameHeight() {
        return HEIGHT;
    }

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        engine = new GameEngine(root, WIDTH, HEIGHT);
        InputHandler inputHandler = new InputHandler(engine);

        inputHandler.attach(scene);
        engine.start();

        stage.setTitle("Stick Hero Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}