package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {
    @Test
    void canMoveToTest() {
        IWorldMap map = new RectangularMap(4, 4);
        Assertions.assertTrue(map.canMoveTo(new Vector2d(2,1)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(5, 4)));
    }

    @Test
    void placeTest() {
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d position = new Vector2d(2,3);
        Animal a = new Animal(map, position);
        Assertions.assertTrue(map.place(a));
    }

    @Test
    void isOccupiedTest() {
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d position = new Vector2d(2,3);
        Animal a = new Animal(map, position);
        map.place(a);
        Assertions.assertTrue(map.isOccupied(position));
        Assertions.assertFalse(map.isOccupied(new Vector2d(0,1)));
    }

    @Test
    void objectAtTest() {
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d position = new Vector2d(2,3);
        Animal a = new Animal(map, position);
        map.place(a);
        Assertions.assertEquals(a, map.objectAt(position)); // powinien być ten sam adres w pamięci
        Assertions.assertNull(map.objectAt(new Vector2d(0, 1)));
    }

}
