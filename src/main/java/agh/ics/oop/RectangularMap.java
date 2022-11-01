package agh.ics.oop;
import java.util.LinkedList;
import java.util.ArrayList;

public class RectangularMap implements IWorldMap{

    final int width, height;
    final Vector2d upperRight, lowerLeft;

    ArrayList<Animal> animalList;

    public RectangularMap(int width, int height) {
        this.width = width - 1;
        this.height = height - 1;
        this.upperRight = new Vector2d(this.width, this.height);
        this.lowerLeft = new Vector2d(0,0);
        this.animalList = new ArrayList<Animal>();
    }

    @Override
    public String toString() {
        MapVisualizer visualization = new MapVisualizer(this);
        return visualization.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(this.upperRight) && position.follows(this.lowerLeft);
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) { // sprawdza czy nowa pozycja jest w ogóle in bounds
            return false;
        }
        if (isOccupied(animal.getPosition())) { //sprawdza czy pole jest zajęte
            return false;
        }
        this.animalList.add(animal); //dodaje zwierzaka
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {  // true jak pole jest zajęte
        for (Animal i: this.animalList) {
            if (i.isAt(position)) {
                return true;
            }
        }
        return false;
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
}
