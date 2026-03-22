package com.example.stickherog.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import com.example.stickherog.engine.GameEngine;
import com.example.stickherog.entities.StickHeroCharacter;

public class InputHandler {

    private GameEngine engine;

    public InputHandler(GameEngine engine) {
        this.engine = engine;
    }

    public void attach(Scene scene) {
        scene.setOnKeyPressed(event -> {
            StickHeroCharacter hero = engine.getStickHero();

            if (event.getCode() == KeyCode.SPACE) {
                hero.extend();
            }

            if (event.getCode() == KeyCode.ENTER) {
                hero.stopStick();
            }

            if (event.getCode() == KeyCode.SHIFT) {
                hero.flip();
            }
        });
    }
}