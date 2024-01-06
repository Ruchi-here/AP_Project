# Stick Hero Game

## Overview

Stick Hero is a game that challenges players to extend a stick to cross a gap and collect cherries. The game features various elements, including different types of cherries, player controls, scoring system, and multiple controllers for different game screens.

## Classes

1. **BlueCherry**
   - Description: Implements the cherry class, representing a blue cherry in the game.

2. **Cherry Interface**
   - Description: A class that defines the cherry interface, serving as a blueprint for cherry classes in the game.

3. **RedCherry**
   - Description: Represents a red cherry in the game.

4. **GameOverController**
   - Description: Controller responsible for handling the game over screen.

5. **MainPageController**
   - Description: Controller responsible for managing the main page of the game.

6. **MovementPlayer**
   - Description: Class responsible for handling the movement of the player in the game.

7. **PauseScreenController**
   - Description: Controller responsible for managing the pause screen of the game.

8. **Player**
   - Description: Represents the player in the game.

9. **PositionOfPlayer**
   - Description: Class that stores the x and y positions of the player.

10. **Controller**
    - Description: Base class for all controllers in the game.

11. **ShowGames**
    - Description: A class that handles the display of games on the main page.

12. **ShowGames.fxml**
    - Description: An FXML file that defines the layout of the `ShowGames` class.

## How to Play

- **Extend Stick:** Press the SPACE key.
- **Toggle Player's Flip:** Press the D key.
- **Pause Game:** Press the P key.
- **Resume Game:** Press the R key.

## Features

- **Score System:** The game tracks the player's score (scoreCounter), updating when the player collects red dots.
- **Background Music:** JavaFX MediaPlayer is used to play background music.

## Commands

1. **Navigate to Your Project Directory:**
   ```
   cd path/to/your/project
   ```

2. **Compile the Java Code:**
   ```
   javac --module-path "path/to/javafx-sdk-xx/lib" --add-modules javafx.controls,javafx.fxml Controller.java
   ```

3. **Run the Java Program:**
   ```
   java --module-path "path/to/javafx-sdk-xx/lib" --add-modules javafx.controls,javafx.fxml com.example.game3.Controller
   ```

## Notes

- Replace "path/to/javafx-sdk-xx" with the actual path to your JavaFX SDK.
