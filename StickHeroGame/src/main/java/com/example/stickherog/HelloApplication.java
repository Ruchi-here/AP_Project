package com.example.stickherog;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 import com.almasb.fxgl.audio.Sound;
 import javafx.animation.AnimationTimer;
 import javafx.animation.FadeTransition;
 import javafx.animation.SequentialTransition;
 import javafx.animation.TranslateTransition;
 import javafx.application.Application;
 import javafx.geometry.Insets;
 import javafx.geometry.Pos;
 import javafx.scene.Group;
 import javafx.scene.Scene;
 import javafx.scene.canvas.Canvas;
 import javafx.scene.canvas.GraphicsContext;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.input.KeyCode;
 import javafx.scene.layout.Pane;
 import javafx.scene.layout.VBox;
 import javafx.scene.paint.Color;
 import javafx.scene.text.Font;
 import javafx.scene.text.FontWeight;
 import javafx.scene.text.Text;
 import javafx.stage.Stage;

 import java.io.File;
 import java.util.ArrayList;
 import java.util.List;

 import javafx.fxml.FXML;
 import javafx.scene.control.Button;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.util.Duration;

import javax.print.attribute.standard.Media;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.junit.Test;
import org.w3c.dom.Entity;

import static org.testng.AssertJUnit.assertEquals;

import static com.sun.webkit.graphics.GraphicsDecoder.SCALE;
public class HelloApplication extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Image backgroundImage;
    private Pane root;
    private Canvas canvas;
    private GraphicsContext gc;
    private StickHeroCharacter stickHero;
    private List<Platform> platforms;
    private List<Reward> rewards;
    private List<reward2> reward2s;
    private List<PoisonReward> poisonrewards;
   private List<Obstacle> obstacles;

    public static boolean isFlipping = false;
    private int score = 0;
    private int cherries = 0;
    public static boolean isStickStopped = false;
    public static boolean hasMovedToStickEnd = false;
    private boolean isGameOver = false;
    static boolean isCharacterOnPlatform = true;
    private VBox reviveBox;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    boolean gameoverflag=false;

   public int getframwidth(){
       return WIDTH*SCALE;
   }
   public int getframeheight(){
       return HEIGHT*SCALE;
   }
    public static Camera cam=new Camera();

    private static final double MIN_PLATFORM_GAP = 200.0;
    private static final double MAX_PLATFORM_GAP = 300.0;

public void tick(){
    cam.tick(stickHero, WIDTH / 4);

}
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Stick Hero Game");
        root = new Pane();
        scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        ((Pane) root).getChildren().add(canvas);

        initializeGame();
        handleInput(scene);
        playBackgroundMusic();
        scene.setOnKeyPressed(event -> {
              if (event.getCode() == KeyCode.ENTER&&StickHeroCharacter.isJumping) {
                  StickHeroCharacter.isJumping=false;
                  stickHero.setY(350);
            }
            if (event.getCode() == KeyCode.SHIFT&&!StickHeroCharacter.isJumping) {
                isCharacterFlipped = !isCharacterFlipped;
                stickHero.setFlipped(isCharacterFlipped);
            }
            if (event.getCode() == KeyCode.SPACE && !isFlipping&&!stickHero.ismoving&& !isCharacterFlipped) {
                stickHero.extend();
                isStickStopped = false;
            }
            if (event.getCode() == KeyCode.ENTER&& !stickHero.ismoving) {
                stickHero.stopStick();
                isStickStopped = true;
            }
            if (event.getCode() == KeyCode.SPACE && stickHero.ismoving&&!isCharacterFlipped) {
                stickHero.jump();
            }
            isFlipping = false;
        });


        new AnimationTimer() {
            long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1e9;
                cam.tick(stickHero, WIDTH / 4);
                update(deltaTime);
                render();
                lastTime = now;

            }
        }.start();

        primaryStage.show();
    }
    private void initializeUI() {
        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> startGame());

        root.getChildren().add(startButton);
    }
    private int getframeheight() {
        return 0;
    }

    private void startGame() {
       playBackgroundMusic();
        new AnimationTimer() {
            long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1e9;
                update(deltaTime);
                render();
                lastTime = now;
            }
        }.start();
    }

    private MediaPlayer bgmPlayer;
    private MediaPlayer rewardplayer;
    private MediaPlayer deadplayer;

    private void playBackgroundMusic() {
        String musicFile = "C:\\Users\\shaur\\Desktop\\game-music-7408.mp3";
        try {
            Path path = Paths.get(musicFile);
            String uri = path.toUri().toString();
            Media media = new Media(uri);
            bgmPlayer = new MediaPlayer(media);
            bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            bgmPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void playrewardMusic() {
        String musicFile ="C:\\Users\\shaur\\Desktop\\coin-upaif-14631.mp3"; 
        try {
            Path path = Paths.get(musicFile);
            String uri = path.toUri().toString();
            Media media = new Media(uri);
            rewardplayer = new MediaPlayer(media);
            rewardplayer.setCycleCount(1);
            rewardplayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void playerdeadMusic() {
        String musicFile ="C:\\Users\\shaur\\Desktop\\male-scream-in-fear-123079.mp3";
        try {
            Path path = Paths.get(musicFile);
            String uri = path.toUri().toString();
            Media media = new Media(uri);
            deadplayer = new MediaPlayer(media);
            deadplayer.setCycleCount(1);
            deadplayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Platform createNewPlatform(double startX, double width) {
        return new Platform(startX, 385, width, 2000);
    }

    private void initializeGame() {
        playBackgroundMusic();
        stickHero = new StickHeroCharacter(0, 350);
        platforms = new ArrayList<>();
        rewards = new ArrayList<>();
        reward2s=new ArrayList<>();
        poisonrewards=new ArrayList<>();


        try {
            backgroundImage = new Image("file:/C:/Users/shaur/Desktop/bgimage.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

        double platformX = 0;
        double rewardX = 25;
        double platformWidth = 50;
        double platformWidthmin = 70;
        double platformWidthmax = 150;
        double rewardX2 = 75;
        double poisonrewardX=75;


        for (int i = 0; i < 500; i++) {
            Platform platform = new Platform(platformX, HEIGHT - 200, platformWidth, 2000);
            platforms.add(platform);
            Reward reward = new Reward(rewardX, 385);
            rewards.add(reward);

            if (i < 499) {
                Platform nextPlatform = new Platform(platformX + platformWidth, HEIGHT - 200, 0, 2000);
                double rewardX2Gap = Math.random() * (MAX_PLATFORM_GAP - MIN_PLATFORM_GAP) + MIN_PLATFORM_GAP;
                double poisonrewardgap = Math.random() * (MAX_PLATFORM_GAP - MIN_PLATFORM_GAP) + MIN_PLATFORM_GAP;
                rewardX2 = platformX + platformWidth + rewardX2Gap + 30+(nextPlatform.getX() - (platformX + platformWidth + rewardX2Gap));
                poisonrewardX = platformX + platformWidth + poisonrewardgap + 80+(nextPlatform.getX() - (platformX + platformWidth + poisonrewardgap));

            }


            reward2 reward2 = new reward2(rewardX2, 420);
            reward2s.add(reward2);

            PoisonReward poisonrew = new PoisonReward(poisonrewardX, 385);
            poisonrewards.add(poisonrew);
            double platformGap = Math.random() * (MAX_PLATFORM_GAP - MIN_PLATFORM_GAP) + MIN_PLATFORM_GAP;
            platformWidth = Math.random() * (platformWidthmax - platformWidthmin) + platformWidthmin;

            platformX += platformWidth + platformGap;
            rewardX += platformWidth + platformGap;
            poisonrewardX+=platformWidth + platformGap;
        }

       platforms.add(new Platform(0, HEIGHT-200, 150, 2000));
       platforms.add(new Platform(300, HEIGHT-200, 100, 2000));
       platforms.add(new Platform(550, HEIGHT-200, 50, 2000));
       platforms.add(new Platform(700, HEIGHT-200, 150, 2000));

       platforms.add(createNewPlatform(0, 150));

       rewards.add(new Reward(100, 385));
       rewards.add(new Reward(350, 385));
       rewards.add(new Reward(570, 385));
       rewards.add(new Reward(740, 385));

    }
    private int getframwidth() {
        return 0;
    }
        private void update(double deltaTime) {
        stickHero.update(deltaTime, platforms.get(0),gc);

            List<Reward> rewardsToRemove = new ArrayList<>();
            List<reward2> reward2toremove=new ArrayList<>();
            List<PoisonReward> poisonrewardtoremove=new ArrayList<>();


            if(hasMovedToStickEnd&&!isCharacterOnPlatform){
                stickHero.fallCharacterTo(stickHero.getX(),500,gc);
            }

            for (Reward reward : rewards) {
                if (stickHero.intersects(reward) || stickHero.getX() > reward.getX()) {
                    playrewardMusic();
                    stickHero.collectReward(reward);
                    rewardsToRemove.add(reward);

                    score += 10; 
                    cherries++;

                    Text perfectText = new Text("PERFECT!");
                    perfectText.setX(reward.getX()-20-cam.getX());
                    perfectText.setY(reward.getY() - 50);
                    perfectText.setFill(Color.GOLD); 
                    perfectText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5 ), perfectText);
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1);

                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), perfectText);
                    fadeOut.setFromValue(1.0);
                    fadeOut.setToValue(0.0);

                    SequentialTransition fade = new SequentialTransition(fadeIn, fadeOut);
                    fade.play();

                    root.getChildren().add(perfectText);
                }
            }
            for (reward2 reward2s : reward2s) {
                if ( stickHero.intersects(reward2s) || stickHero.getX() < reward2s.getX()+5&& stickHero.getX() > reward2s.getX()-5 && isCharacterFlipped) {
                    playrewardMusic();
                    stickHero.collectreward2(reward2s);
                    reward2toremove.add(reward2s);

                    score += 10;
                    cherries++;
                }
            }
            for (PoisonReward poisonReward : poisonrewards) {
                if ( stickHero.intersects(poisonReward) || stickHero.getX() < poisonReward.getX()+5&& stickHero.getX() > poisonReward.getX()-5 && !StickHeroCharacter.isJumping&&!isCharacterFlipped) {
                    playerdeadMusic();
                    stickHero.collectreward2(poisonReward);
                    poisonrewardtoremove.add(poisonReward);
                    displayGameOverMessage();
                    playerdeadMusic();
                    bgmPlayer.stop();
                    isGameOver = true;
                    displayReviveBox();


                }
            }
           if(isCharacterFlipped&&isCharacterOnPlatform){
               isCharacterFlipped=false;
               displayGameOverMessage();
               playerdeadMusic();
               bgmPlayer.stop();
               isGameOver = true;
               displayReviveBox();

               stickHero.land();
           }

            rewards.removeAll(rewardsToRemove);
            reward2s.removeAll(reward2toremove);
            poisonrewards.removeAll(poisonrewardtoremove);

            if (!isFlipping && isStickStopped && !hasMovedToStickEnd) {
                double stickEnd = stickHero.getX() + stickHero.getStickLength();
                double characterWidth = stickHero.getCharacterImage().getWidth();
                double characterEnd = stickEnd + characterWidth; // Calculate character's end position

            if (characterEnd > 1000) {
               StickHeroCharacter.x+=20*deltaTime;
                stickHero.moveCharacterTo(stickEnd,350,gc);
                hasMovedToStickEnd = true;
            }
            else {
               StickHeroCharacter.x+=20*deltaTime;
                stickHero.moveCharacterTo(stickEnd,350,gc);
                hasMovedToStickEnd = true;

            }
                double characterSpeed = 20;

                for (Platform platform : platforms) {
                double platformStartX = platform.getX();
                double platformEndX = platformStartX + platform.getWidth();

                if (stickHero.getX() >= platformStartX-10 && stickHero.getX() <= platformEndX-10) {
                    isCharacterOnPlatform = true;
                    break;
                }
                else{
                    isCharacterOnPlatform=false;
                }
            }

            if ((!isCharacterOnPlatform &&hasMovedToStickEnd&&!stickHero.ismoving)) {
                stickHero.fallCharacterTo(stickHero.getX(),500,gc);
                displayGameOverMessage();
                playerdeadMusic();
                bgmPlayer.stop();


                isGameOver = true;
                    displayReviveBox();

                stickHero.land();
            }
        }
            if (isStickStopped && hasMovedToStickEnd&&!isCharacterOnPlatform) {
                stickHero.fallCharacterTo(stickHero.getX(),500,gc);
            }

        if (isFlipping) {
            for (Reward reward : rewards) {
                if (stickHero.intersects(reward)) {
                    stickHero.collectReward(reward);
                    rewards.remove(reward);
                    score += 10; 
                    cherries++;
                    break;
                }
            }
        }


        if (stickHero.getY() > HEIGHT) {
            if (cherries > 0) {
                cherries--;
                stickHero.revive();
            } else {
                initializeGame();
                score = 0;
            }
        }


        if (hasMovedToStickEnd && stickHero.getStickLength() == 0) {
            stickHero.resetStick(); 
            hasMovedToStickEnd = false;
            isStickStopped = false;
            isFlipping = false;
        }
    }


    private void displayReviveBox() {
        reviveBox = new VBox();
        reviveBox.setAlignment(Pos.CENTER);
        reviveBox.setSpacing(20);
        reviveBox.setPadding(new Insets(180,400,300,320));

        Text reviveText = new Text("Revive?");
        reviveText.setFill(Color.BLACK);
        reviveText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        reviveText.setLayoutX(300);
        reviveText.setLayoutY(300);



        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        reviveBox.getChildren().addAll(reviveText, yesButton, noButton);
        root.getChildren().add(reviveBox);

        yesButton.setOnAction(e -> {

            root.getChildren().remove(reviveBox);
            reviveBox.getChildren().removeAll(reviveText, yesButton, noButton);
            if (cherries > 1) {
                cherries-=2;
                score-=20;
                removeGameOverMessage();
                gameoverflag=false;
                reviveBox.getChildren().removeAll(reviveText, yesButton, noButton);
                root.getChildren().remove(reviveBox);
                restartGame();
            }
            else {
                Text notEnoughCandyText = new Text("Not enough candy for reviving");
                notEnoughCandyText.setFill(Color.RED);
                notEnoughCandyText.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                notEnoughCandyText.setLayoutX(300);
                notEnoughCandyText.setLayoutY(250);

                root.getChildren().add(notEnoughCandyText);

                FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), notEnoughCandyText);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setOnFinished(event1 -> root.getChildren().remove(notEnoughCandyText));
                fadeOut.play();
                cherries=0;
                score=0;
                removeGameOverMessage();
                restartGame();
            }
        });

        noButton.setOnAction(e -> {
            removeGameOverMessage();
            cherries=0;
            score=0;
            reviveBox.getChildren().removeAll(reviveText, yesButton, noButton);
            root.getChildren().remove(reviveBox);
            restartGame();
        });

    }
    private void removeReviveBox() {
        root.getChildren().remove(reviveBox);
        reviveBox = null;
    }
    public static boolean isCharacterFlipped = false;
    private void handleInput(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && StickHeroCharacter.isJumping) {
                StickHeroCharacter.isJumping=false;
                stickHero.setY(350);
            }
            if (event.getCode() == KeyCode.SHIFT&&!StickHeroCharacter.isJumping) {
                isCharacterFlipped = !isCharacterFlipped;
                stickHero.setFlipped(isCharacterFlipped);
            }
            if (event.getCode() == KeyCode.SPACE && !isFlipping&&!stickHero.ismoving&&!isCharacterFlipped) {
                stickHero.extend();
                isStickStopped = false;
            }
            if (event.getCode() == KeyCode.ENTER&&!stickHero.ismoving) {
                stickHero.stopStick();
                isStickStopped = true;
            }
            if (event.getCode() == KeyCode.SPACE && stickHero.ismoving&&!isCharacterFlipped) {
                stickHero.jump();
            }
            isFlipping = false;
        });
    }
    private void displayGameOverMessage() {
        Text gameOverText = new Text("Game Over!");
        gameOverText.setX(300);
        gameOverText.setY(100);
        gameOverText.setFill(Color.RED);
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 30)); 

        root.getChildren().add(gameOverText);
    }
    private void restartGame() {
        bgmPlayer.play();
        isCharacterFlipped = false;
        isGameOver = false;
        initializeGame();
        handleInput(scene);


    }
    private void removeGameOverMessage() {
        root.getChildren().removeIf(node -> node instanceof Text && ((Text) node).getText().equals("Game Over!"));
        gameoverflag=false;
    }
    private void render() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT);
        stickHero.renderCharacter(gc);
        stickHero.renderStick(gc);

        for (Platform platform : platforms) {
            platform.render(gc,- cam.getX());
        }

        for (Reward reward : rewards) {
            reward.render(gc, cam.getX());
        }

        for (reward2 reward2 : reward2s) {
            reward2.render(gc, cam.getX());
        }
        for (PoisonReward poisonrew : poisonrewards) {
            poisonrew.render(gc, cam.getX());
        }
        Font font = Font.font("Arial", FontWeight.BOLD, 14);
        gc.setFont(font);
        gc.setFill(Color.GOLD);
        gc.fillText("Score: " + score, 700, 30);
        Image cherriesImage = new Image("file:/C:/Users/shaur/Desktop/cherry.png"); 
        gc.drawImage(cherriesImage, 690, 30,50,50);
        gc.fillText(": " + cherries, 740, 60);

    }
    private void simulateKeyPress(KeyCode key) {
       Robot robot = new Robot();
       robot.keyPress(key);
       robot.keyRelease(key);
    }

@Test
void testGetFrameWidth() {
    HelloApplication app = new HelloApplication();
    assertEquals(21600, app.getframwidth());
}
    @Test
    void testGetFrameHeight() {
        HelloApplication app = new HelloApplication();
        assertEquals(16200, app.getframeheight());
    }


}
