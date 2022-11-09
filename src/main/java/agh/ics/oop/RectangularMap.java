package agh.ics.oop;
import java.util.LinkedList;
import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap{

    final int width, height;

    public RectangularMap(int width, int height) {
        this.width = width - 1;
        this.height = height - 1;
        this.upperRight = this.printUpperRight = new Vector2d(this.width, this.height);
        this.lowerLeft = this.printLowerLeft = new Vector2d(0,0);
        this.animalList = new ArrayList<Animal>();
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal i: this.animalList) {
            if (i.isAt(position)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void setPrintBounds() {
        printLowerLeft = this.lowerLeft;
        printUpperRight = this.upperRight;
    }
}
