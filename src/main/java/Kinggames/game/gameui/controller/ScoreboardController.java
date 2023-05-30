package Kinggames.game.gameui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.tinylog.Logger;


public class ScoreboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> CreateSteps;

    @FXML
    private TableColumn<?, ?> Time;

    @FXML
    private TableColumn<?, ?> User;

    @FXML
    private TableView<?> scoreBoard;

    @FXML
    void LoadScores(ActionEvent event) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build("output.xml");


            Element rootElement = document.getRootElement();


            Element nameElement = rootElement.getChild("Name");
            String name = nameElement.getText();
            Logger.debug("Name"+name);


            Element StepElement = rootElement.getChild("Step");
            int Step = Integer.parseInt(StepElement.getText());
            Logger.debug("Step"+Step);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void TryButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void initialize() {
        assert CreateSteps != null : "fx:id=\"CreateSteps\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert Time != null : "fx:id=\"Time\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert scoreBoard != null : "fx:id=\"scoreBoard\" was not injected: check your FXML file 'scoreboard.fxml'.";

    }

}
