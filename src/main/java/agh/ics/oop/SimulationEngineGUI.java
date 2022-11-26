package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngineGUI implements IEngine, Runnable {

    private ArrayList<IGUIObserver> observerList = new ArrayList<IGUIObserver>();
    private MoveDirection[] direction;
    private final IWorldMap map;
    private final Vector2d[] position;

    private final int moveDelay = 1000;

    public SimulationEngineGUI(MoveDirection[] direction, IWorldMap map, Vector2d[] position) {
        this.direction = direction;
        this.map = map;
        this.position = position;
    }

    public SimulationEngineGUI(IWorldMap map, Vector2d[] position) {
        this.map = map;
        this.position = position;
    }

    public void addObserver(IGUIObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(IGUIObserver observer) {
        observerList.remove(observer);
    }

    void render() {
        for (IGUIObserver observer : observerList) {
            observer.render();
        }
    }

    public void directionSetter(MoveDirection[] newDirections) {
        this.direction = newDirections;
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
            render();
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException ex) {
                System.out.println("interrupted exception on sleep");
            }
            current += 1;
            current = current % animalList.size();
        }

    }
}
