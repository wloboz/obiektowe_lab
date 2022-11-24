package agh.ics.oop;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {

    public VBox vbox;

    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        String fileAddress = "src/main/resources/" + element.getFileName();
        Image image = new Image(new FileInputStream(fileAddress));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Label label = new Label(element.getPosition().toString());
        VBox vbox = new VBox(imageView, label);
        vbox.setAlignment(Pos.CENTER);
    }

}
