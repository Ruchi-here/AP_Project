package com.example.stickherog.engine;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import com.example.stickherog.entities.*;
import com.example.stickherog.audio.AudioManager;
import com.example.stickherog.ui.GameUIManager;

public class GameEngine {

    private Pane root;
    private Canvas canvas;
    private GraphicsContext gc;

    private int width;
    private int height;

    private StickHeroCharacter stickHero;
    private List<Platform> platforms = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();

    private int score = 0;
    private int cherries = 0;

    private Image background;
    private AnimationTimer timer;

    private AudioManager audioManager;
    private GameUIManager uiManager;

    public GameEngine(Pane root, int width, int height) {
        this.root = root;
        this.width = width;
        this.height = height;

        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        audioManager = new AudioManager();
        uiManager = new GameUIManager(root);

        init();
    }

    private void init() {
        stickHero = new StickHeroCharacter(0, 350);

        platforms.add(new Platform(0, height - 200, 150, 2000));
        platforms.add(new Platform(300, height - 200, 100, 2000));

        rewards.add(new Reward(100, 385));

        background = new Image("file:/bg.png");

        audioManager.playBackground();
    }

    public void start() {
        timer = new AnimationTimer() {
            long last = System.nanoTime();

            @Override
            public void handle(long now) {
                double delta = (now - last) / 1e9;
                update(delta);
                render();
                last = now;
            }
        };
        timer.start();
    }

    private void update(double dt) {
        stickHero.update(dt, platforms.get(0), gc);

        rewards.removeIf(r -> {
            if (stickHero.intersects(r)) {
                score += 10;
                cherries++;
                audioManager.playReward();
                return true;
            }
            return false;
        });

        if (stickHero.getY() > height) {
            audioManager.playDead();
            uiManager.showGameOver(() -> restart());
        }
    }

    private void render() {
        gc.clearRect(0, 0, width, height);
        gc.drawImage(background, 0, 0, width, height);

        stickHero.renderCharacter(gc);

        for (Platform p : platforms) {
            p.render(gc, 0);
        }

        for (Reward r : rewards) {
            r.render(gc, 0);
        }

        gc.fillText("Score: " + score, 700, 30);
    }

    public StickHeroCharacter getStickHero() {
        return stickHero;
    }

    public void restart() {
        score = 0;
        cherries = 0;
        platforms.clear();
        rewards.clear();
        init();
    }
}