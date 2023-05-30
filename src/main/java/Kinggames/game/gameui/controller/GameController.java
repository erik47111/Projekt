package Kinggames.game.gameui.controller;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Kinggames.game.gameui.state.*;
import com.sun.jdi.event.BreakpointEvent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.tinylog.Logger;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;





import Kinggames.game.gameui.state.Position;
import Kinggames.game.gameui.state.Position;

import javax.swing.*;


import static Kinggames.game.gameui.state.State.Floor;

public class GameController {



    @FXML
    private GridPane Board;

    @FXML
    private Button ExitButton;

    @FXML
    private Button RestartButton;

    @FXML
    private Label Winlabel;

    @FXML
    private Button Save;
    private Position red;
    private Position blue;
    private Position Temp;

    private  Position Temp2;

    private State state;
    private State Blue_state;

    int RedStep;
    int Bluestep;

    Circle red_circle;

    Circle Blue_Cicle;

    Circle Black_circle;
    boolean who;
    String redstep_string;
    String Red_name;


    boolean stopEventHandling;

    boolean stopEventHandlingcheck;
    @FXML
    void Save(ActionEvent event) {
        Element rootElement = new Element("root");
        Document document = new Document(rootElement);


        rootElement.addContent(new Element("Name").setText(Red_name));
        rootElement.addContent(new Element("Step").setText(redstep_string));



        try {
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            FileWriter writer = new FileWriter("output.xml");
            xmlOutputter.output(document, writer);
            writer.close();
            Logger.info("output.xml sikeresen létrehozva");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void Goback(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }


    @FXML
    void Reload(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private int getRowIndexByY(double y, GridPane gridPane) {
        double totalHeight = gridPane.getHeight();
        double rowHeight = totalHeight / gridPane.getRowConstraints().size();
        int rowIndex = (int) (y / rowHeight);
        return rowIndex;
    }
    private int getColumnIndexByX(double x, GridPane gridPane) {
        double totalWidth = gridPane.getWidth();
        double columnWidth = totalWidth / gridPane.getColumnConstraints().size();
        int columnIndex = (int) (x / columnWidth);
        return columnIndex;
    }
    @FXML
    public void initialize() {
        alapállapot();
        who=true;
        //Checkstep();
        RedStep=0;
        Red_name="Red";


    }

        @FXML
    public void movenement() {
            Board.setOnMouseClicked(event -> {
                        Node clickedNode = event.getPickResult().getIntersectedNode();
                        double x = event.getX();
                        double y = event.getY();
                        int columnIndex = getColumnIndexByX(x, Board);
                        int rowIndex = getRowIndexByY(y, Board);




                if (clickedNode != null && clickedNode instanceof Circle) {
                    Circle clickedCircle = (Circle) clickedNode;

                    Logger.warn("Hibás lépés,kérlek lépj máshová."+clickedCircle);

                }else {
                    if (who){
                        Temp=new Position(rowIndex,columnIndex);
                        Logger.debug("row "+rowIndex+" col "+columnIndex);
                        Board.getChildren().remove(red_circle);
                        Board.setConstraints(red_circle, columnIndex,rowIndex );
                        RedStep++;
                        redstep_string=""+RedStep;
                        Board.getChildren().add(red_circle);
                        Destroy();
                        see();
                        who=false;

                    }else {

                        Temp=new Position(columnIndex,rowIndex);
                        Board.getChildren().remove(Blue_Cicle);
                        Board.setConstraints(Blue_Cicle, columnIndex,rowIndex );
                        Logger.debug("Row "+rowIndex+" col "+columnIndex);
                        Board.getChildren().add(Blue_Cicle);
                        who=true;
                        Destroy();
                        see();
                    }
                }

          });
    }








    private void Destroy() {
        Board.setOnMouseClicked(event -> {
            if (stopEventHandling) {
                movenement();
                stopEventHandling=false;
                return;
            }
            Node clickedNode = event.getPickResult().getIntersectedNode();

            if (clickedNode != null && clickedNode instanceof Circle) {
                Circle clickedCircle = (Circle) clickedNode;
                Logger.warn("Ez egy érvénytelen lépés,pusztíts el egy másik mezőt" + clickedCircle);
            }else {
                double x = event.getX();
                double y = event.getY();
                int columnIndex = getColumnIndexByX(x, Board);
                int rowIndex = getRowIndexByY(y, Board);



                Black_circle = new Circle(21);
                Black_circle.setFill(Color.BLACK);
                Black_circle.setId("Black");
                Board.add(Black_circle,columnIndex,rowIndex);
                stopEventHandling=true;


            }

            
                });

    }

public void Checkstep(){
    Board.setOnMouseClicked(event -> {
        if (stopEventHandlingcheck) {
            movenement();
            stopEventHandlingcheck=false;
            return;
        }
        Node clickedNode = event.getPickResult().getIntersectedNode();

        if (clickedNode != null && clickedNode instanceof Circle) {
            Circle clickedCircle = (Circle) clickedNode;
            Logger.warn("Hibás lépés,kérlek lépj máshová."+clickedCircle);
        }else {
            Logger.debug("üres mező");
            //stopEventHandlingcheck=true;


        }

    });
}




    public void alapállapot(){
        red_circle = new Circle(23);
        red_circle.setFill(Color.RED);
        red_circle.setId("red");
        Blue_Cicle = new Circle(23);
        Blue_Cicle.setFill(Color.BLUE);
        Blue_Cicle.setId("Blue");
        Board.setConstraints(red_circle, 0, 2);
        Board.setConstraints(Blue_Cicle, 7, 3);


        Board.getChildren().addAll(red_circle, Blue_Cicle);


    }



        public void see(){
            ObservableList<Node> nodes = Board.getChildren();
            for (Node node : nodes) {

                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);

                Logger.debug("Elem "+node+"található a(z) "+rowIndex+" sor "+columnIndex+" oszlop ");
            }
        }



        }




