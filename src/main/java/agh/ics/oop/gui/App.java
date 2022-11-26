package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class App extends javafx.application.Application implements IGUIObserver {

    private double gridSize = 40;
    private int moveDelay = 300;

    private AbstractWorldMap map = new GrassField(3);
    private GridPane grid = new GridPane();

    Button startButton = new Button("Start");
    TextField inputField = new TextField();


    @Override
    public void start(Stage primaryStage) throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] direction = new OptionsParser().parse(args);
        Vector2d[] position = {new Vector2d(1, -1), new Vector2d(2, 4)};

        Scene uiScene = new Scene(new VBox(startButton,new HBox(new Label("input:"),inputField)));
        Stage secondStage = new Stage();
        secondStage.setTitle("controls");
        secondStage.setHeight(100);
        secondStage.setWidth(300);
        secondStage.setX(100);
        secondStage.setAlwaysOnTop(true);
        secondStage.setScene(uiScene);
        secondStage.show();

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        SimulationEngineGUI engine = new SimulationEngineGUI(map, position);
        engine.addObserver(this);

        startButton.setOnAction(e -> {
            String[] newMoves = inputField.getCharacters().toString().split(" ");
            MoveDirection[] newDirection = new OptionsParser().parse(newMoves);
            engine.directionSetter(newDirection);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });
    }

    public void draw() throws FileNotFoundException {
        System.out.println(map.printLowerLeft.toString() + " " + map.printUpperRight.toString());
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(10, 10, 10, 10));
        drawHeader(map, grid);
        drawObjects(map, grid);

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
        int rowIndex = 0;
        for (int i = map.printLowerLeft.y; i <= map.printUpperRight.y; i++)
        {
            label = new Label(String.format("%d", i));
            rowIndex = map.printUpperRight.y - i + 1;
            grid.add(label, 0, rowIndex );
            grid.getRowConstraints().add(new RowConstraints(this.gridSize));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    void drawObjects(AbstractWorldMap map, GridPane grid) throws FileNotFoundException {
        for (int i = map.printLowerLeft.x; i <= map.printUpperRight.x; i++) {
            for (int j = map.printLowerLeft.y; j <= map.printUpperRight.y; j++) {
                Object toAdd = map.objectAt(new Vector2d(i, j));
                if (toAdd == null) {
                    continue;
                }
                IMapElement toAddElement = (IMapElement) toAdd;
                GuiElementBox box = new GuiElementBox(toAddElement);
                grid.add(box.vbox, i + 1 + Math.abs(map.printLowerLeft.x), map.printUpperRight.y - j + 1);
                GridPane.setHalignment(box.vbox, HPos.CENTER);
            }
        }
    }

}
