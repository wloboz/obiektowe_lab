package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] direction;
    private final IWorldMap map;
    private final Vector2d[] position;

    public SimulationEngine(MoveDirection[] direction, IWorldMap map, Vector2d[] position) {
        this.direction = direction;
        this.map = map;
        this.position = position;
    }

    @Override
    public void run() {
        ArrayList<Animal> animalList = new ArrayList<Animal>();
        for (Vector2d i: position) {
            Animal newAnimal = new Animal(map, i);
            if (map.place(newAnimal)) {
                animalList.add(newAnimal);
            }
        }
        int current = 0;
        for (MoveDirection i: direction) {
            animalList.get(current).move(i);
            current += 1;
            current = current % animalList.size();
        }
    }
}
