package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class EngineIntegratedTest {

    @Test
    void setupTest() {
        IWorldMap map = new RectangularMap(3, 2);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(1,1)};
        ArrayList<Animal> animalList = new ArrayList<Animal>();
        for (Vector2d i: positions) {
            Animal newAnimal = new Animal(map, i);
            if (map.place(newAnimal)) {
                animalList.add(newAnimal);
            }
        }
        Assertions.assertEquals(2, animalList.size());
    }

    @Test
    void advancedTest() {
        String[] testInstruction = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(testInstruction);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Vector2d expected1 = new Vector2d(3,4);
        Vector2d expected2 = new Vector2d(2,0);
        Vector2d expected3 = new Vector2d(0,0);
        Assertions.assertTrue(map.isOccupied(expected1));
        Assertions.assertTrue(map.isOccupied(expected2));
        Assertions.assertFalse(map.isOccupied(expected3));
        Object object1 = map.objectAt(expected1);
        Object object2 = map.objectAt(expected2);
        Object object3 = map.objectAt(expected3);
        Assertions.assertTrue(object1 instanceof Animal && object2 instanceof Animal && object3 == null);
        Animal animal1 = (Animal)object1;
        Animal animal2 = (Animal)object2;
        Assertions.assertEquals(MapDirection.NORTH, animal1.getDirection());
        Assertions.assertEquals(MapDirection.SOUTH, animal2.getDirection());
    }

    @Test
    void integratedGrassTest() {
        IWorldMap map = new GrassField(10);
        MoveDirection[] direction = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        Vector2d[] position = { new Vector2d(2,2), new Vector2d(3, 4) };
        SimulationEngine engine = new SimulationEngine(direction, map, position);
        engine.run();
        Vector2d expected1 = new Vector2d(3,7);
        Vector2d expected2 = new Vector2d(2,-1);
        Assertions.assertTrue(map.isOccupied(expected1));
        Assertions.assertTrue(map.isOccupied(expected2));
        Object object1 = map.objectAt(expected1);
        Object object2 = map.objectAt(expected2);
        Object object3 = map.objectAt(new Vector2d(-2,-2));
        Assertions.assertTrue(object1 instanceof Animal && object2 instanceof Animal && object3 == null);
        Animal animal1 = (Animal)object1;
        Animal animal2 = (Animal)object2;
        Assertions.assertEquals(MapDirection.NORTH, animal1.getDirection());
        Assertions.assertEquals(MapDirection.SOUTH, animal2.getDirection());
    }

}
