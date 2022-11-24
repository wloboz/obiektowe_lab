package agh.ics.oop;

public class Grass implements IMapElement {

    Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String getFileName() {
        return "grass.png";
    }

    @Override
    public String toString() {
        return "*";
    }
}
