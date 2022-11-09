package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {

    Vector2d lowerLeft, upperRight;
    Vector2d printLowerLeft, printUpperRight;
    ArrayList<Animal> animalList = new ArrayList<>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(this.upperRight) && position.follows(this.lowerLeft);
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
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) { // sprawdza czy nowa pozycja jest w ogóle in bounds
            return false;
        }
        if (isOccupied(animal.getPosition()) && (objectAt(animal.getPosition()) instanceof Animal)) {
            //sprawdza czy pole jest zajęte przez zwierzaka, ignoruje inne rzeczy
            return false;
        }
        this.animalList.add(animal); //dodaje zwierzaka
        return true;
    }

    @Override
    public String toString() {
        MapVisualizer visualization = new MapVisualizer(this);
        setPrintBounds();
        return visualization.draw(this.printLowerLeft, this.printUpperRight);
    }

    abstract public void setPrintBounds();

}
