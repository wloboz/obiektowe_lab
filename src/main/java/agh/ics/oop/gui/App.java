package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class App extends javafx.application.Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        AbstractWorldMap map = new GrassField(3);
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] direction = new OptionsParser().parse(args);
        Vector2d[] position = {new Vector2d(4, 0), new Vector2d(7,8)};
        SimulationEngine engine = new SimulationEngine(direction, map, position);
        engine.run();

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(10, 10, 10, 10));

        drawHeader(map, grid);
        drawObjects(map, grid);

        System.out.println(map.toString());



        //GridPane.setHalignment(label, HPos.CENTER);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void drawHeader(AbstractWorldMap map, GridPane grid) {
        Label label = new Label("y\\x");
        grid.add(label, 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(20));
        grid.getRowConstraints().add(new RowConstraints(20));
        GridPane.setHalignment(label, HPos.CENTER);
        for (int i = map.printLowerLeft.x; i <= map.printUpperRight.x; i++) {
            label = new Label(String.format("%d", i));
            grid.add(label, i - map.printLowerLeft.x + 1, 0);
            grid.getColumnConstraints().add(new ColumnConstraints(20));
            GridPane.setHalignment(label, HPos.CENTER);

        }
        for (int i = map.printUpperRight.y; i >= map.printLowerLeft.y; i--) {
            label = new Label(String.format("%d", map.printUpperRight.y - i));
            grid.add(label, 0, i - map.printLowerLeft.y + 1);
            grid.getRowConstraints().add(new RowConstraints(20));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    void drawObjects(AbstractWorldMap map, GridPane grid) {
        for (int i = map.printLowerLeft.x; i <= map.printUpperRight.x; i++) {
            for (int j = map.printUpperRight.y; j >= map.printLowerLeft.y; j--) {
                Object toAdd = map.objectAt(new Vector2d(i, j));
                if (toAdd == null) {
                    continue;
                }
                Label label = new Label(toAdd.toString());
                grid.add(label, i + 1, map.printUpperRight.y - j + 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
    }

}
