package Kinggames.game.gameui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Menu_controller {





    @FXML
    private Button StartButton;

    @FXML
    private Button scoreButton;

    @FXML
    private TextField userInput;

    @FXML
    void Loadgame(ActionEvent event) throws IOException {
    if (userInput.getText().isEmpty()){
        userInput.setPromptText("cant be empty");
    }else {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    }

    @FXML
    void Loadscore(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert StartButton != null : "fx:id=\"StartButton\" was not injected: check your FXML file 'menu.fxml'.";
        assert scoreButton != null : "fx:id=\"scoreButton\" was not injected: check your FXML file 'menu.fxml'.";
        assert userInput != null : "fx:id=\"userInput\" was not injected: check your FXML file 'menu.fxml'.";

    }

}
