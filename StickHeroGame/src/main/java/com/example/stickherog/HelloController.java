package com.example.stickherog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HelloController{

   @FXML
   private void startGame(ActionEvent event) {
       HelloApplication.startGame();
   }
}
