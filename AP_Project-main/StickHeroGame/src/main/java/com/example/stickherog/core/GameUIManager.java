package com.example.stickherog.ui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class GameUIManager {

    private Pane root;

    public GameUIManager(Pane root) {
        this.root = root;
    }

    public void showGameOver(Runnable onRestart) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        Text text = new Text("Game Over");
        Button restart = new Button("Restart");

        restart.setOnAction(e -> {
            root.getChildren().remove(box);
            onRestart.run();
        });

        box.getChildren().addAll(text, restart);
        box.setLayoutX(300);
        box.setLayoutY(200);

        root.getChildren().add(box);
    }
}