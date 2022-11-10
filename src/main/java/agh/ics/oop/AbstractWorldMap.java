package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    Vector2d lowerLeft, upperRight;
    Vector2d printLowerLeft, printUpperRight;
    //ArrayList<Animal> animalList = new ArrayList<>();
    Map<Vector2d, Animal> animalList= new HashMap<>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(this.upperRight) && position.follows(this.lowerLeft);
    }

    @Override
    public boolean isOccupied(Vector2d position) {  // true jak pole jest zajęte
        return animalList.get(position) != null;
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
        this.animalList.put(animal.getPosition(), animal); //dodaje zwierzaka
        animal.addObserver(this);
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalList.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animalList.put(newPosition, animalList.get(oldPosition));
        animalList.remove(oldPosition);
    }

    @Override
    public String toString() {
        MapVisualizer visualization = new MapVisualizer(this);
        setPrintBounds();
        return visualization.draw(this.printLowerLeft, this.printUpperRight);
    }

    abstract public void setPrintBounds();

}
