package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class App extends javafx.application.Application implements IGUIObserver {

    private double gridSize = 40;
    private int moveDelay = 300;

    private AbstractWorldMap map = new GrassField(3);
    private GridPane grid = new GridPane();


    @Override
    public void start(Stage primaryStage) throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] direction = new OptionsParser().parse(args);
        Vector2d[] position = {new Vector2d(4, 0), new Vector2d(3,1)};

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        SimulationEngineGUI engine = new SimulationEngineGUI(direction, map, position);
        engine.addObserver(this);
        Thread engineThread = new Thread(engine);
        engineThread.start();
        //SimulationEngine engine = new SimulationEngine(direction, map, position);
        //engine.run();
        //System.out.println(map.toString());


    }

    public void draw() throws FileNotFoundException {
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(10, 10, 10, 10));
        drawHeader(map, grid);
        drawObjects(map, grid);
        System.out.println(map.printLowerLeft.toString() + " " + map.printUpperRight.toString());
    }


    public void render() {
        Platform.runLater(() -> {
            grid.getChildren().clear();
            grid.setGridLinesVisible(false);
            try {
                draw();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }


    void drawHeader(AbstractWorldMap map, GridPane grid) {
        Label label = new Label("y\\x");
        grid.add(label, 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(this.gridSize));
        grid.getRowConstraints().add(new RowConstraints(this.gridSize));
        GridPane.setHalignment(label, HPos.CENTER);
        for (int i = map.printLowerLeft.x; i <= map.printUpperRight.x; i++) {
            label = new Label(String.format("%d", i));
            grid.add(label, i - map.printLowerLeft.x + 1, 0);
            grid.getColumnConstraints().add(new ColumnConstraints(this.gridSize));
            GridPane.setHalignment(label, HPos.CENTER);

        }
        for (int i = map.printUpperRight.y; i >= map.printLowerLeft.y; i--) {
            label = new Label(String.format("%d", map.printUpperRight.y - i));
            grid.add(label, 0, i - map.printLowerLeft.y + 1);
            grid.getRowConstraints().add(new RowConstraints(this.gridSize));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    void drawObjects(AbstractWorldMap map, GridPane grid) throws FileNotFoundException {
        for (int i = map.printLowerLeft.x; i <= map.printUpperRight.x; i++) {
            for (int j = map.printUpperRight.y; j >= map.printLowerLeft.y; j--) {
                Object toAdd = map.objectAt(new Vector2d(i, j));
                if (toAdd == null) {
                    continue;
                }
                IMapElement toAddElement = (IMapElement) toAdd;
                //Label label = new Label(toAdd.toString());
                GuiElementBox box = new GuiElementBox(toAddElement);
                grid.add(box.vbox, i + 1, map.printUpperRight.y - j + 1);
                GridPane.setHalignment(box.vbox, HPos.CENTER);
            }
        }
    }

}
