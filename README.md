# AP_Project

Stick hero game includes the following classes:

1)BlueCherry - It implements cherry class. class that represents a blue cherry in the game. \n
2)cherry Interface - class that represents a cherry in the game.
3)RedCherry - class that represents a red cherry in the game.
4)GameOverController - controller that handles the game over screen.
5)MainPageController - controller that handles the main page of the game.
6)Movement_player - class that handles the movement of the player in the game.
7)PauseScreenController - controller that handles the pause screen of the game.
8)Player -  class that represents the player in the game.
9)position_of_player - class that stores x position and y position of the player.
10)Controller - base class for all controllers in the game
11)ShowGames - A class that handles the display of games on the main page.
12)ShowGames.fxml - An FXML file that defines the layout of the ShowGames class.


How to Play:
Press the SPACE key to extend the stick.
Press the D key to toggle the player's flip.
Press the P key to pause the game.
Press the R key to resume the game if paused.

Features:
Score System:The game keeps track of the player's score (scoreCounter), which is updated when the player collects red dots.
Background music is played using JavaFX MediaPlayer.


Cmd commands:
1)Navigate to Your Project Directory:
cd path/to/your/project


2)Compile the Java Code:
javac --module-path "path/to/javafx-sdk-xx/lib" --add-modules javafx.controls,javafx.fxml Controller.java


3)Run the Java Program:
java --module-path "path/to/javafx-sdk-xx/lib" --add-modules javafx.controls,javafx.fxml com.example.game3.Controller
