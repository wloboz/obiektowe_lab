package agh.ics.oop.gui;

import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class App extends javafx.application.Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Zwierzak");
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.add(label, 1, 1, 1, 1);
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
