package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    void canMoveToTest() {
        IWorldMap map = new GrassField(4);
        Assertions.assertTrue(map.canMoveTo(new Vector2d(2,1)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(-1000, -4)));
    }

    @Test
    void placeTest() {
        IWorldMap map = new GrassField(4);
        Vector2d position = new Vector2d(2,3);
        Animal a = new Animal(map, position);
        Assertions.assertTrue(map.place(a));
    }

    @Test
    void isOccupiedTest() {
        IWorldMap map = new GrassField(1);
        Vector2d position = new Vector2d(5,3);
        Animal a = new Animal(map, position);
        map.place(a);
        Assertions.assertTrue(map.isOccupied(position));
        Assertions.assertFalse(map.isOccupied(new Vector2d(-10,1)));
        int touchedGrass = 0;
        Vector2d testVector;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                testVector = new Vector2d(i, j);
                if (map.isOccupied(testVector)) {
                    touchedGrass += 1;
                }
            }
        }
        Assertions.assertEquals(1, touchedGrass);
    }

    @Test
    void objectAtTest() {
        IWorldMap map = new GrassField(1);
        Vector2d position = new Vector2d(5,3);
        Animal a = new Animal(map, position);
        map.place(a);
        Assertions.assertEquals(a, map.objectAt(position));
        Assertions.assertNull(map.objectAt(new Vector2d(-10,3)));
        Object newObject;
        Vector2d testVector;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                testVector = new Vector2d(i, j);
                if (map.isOccupied(testVector)) {
                    newObject = map.objectAt(testVector);
                    Assertions.assertTrue(newObject instanceof Grass);
                    return;
                }
            }
        }

    }
}
