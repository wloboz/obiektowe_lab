package agh.ics.oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalIntegratedTest {
    @Test
    void setupTest() {
        IWorldMap map = new RectangularMap(4, 4);
        Animal a = new Animal(map);
        Vector2d test_position = new Vector2d(2,2);
        map.place(a);
        Assertions.assertTrue(a.isAt(test_position));
        Assertions.assertEquals("N", a.toString());
    }

    @Test
    void basicTest() {
        String[] args = {"f", "forward", "r", "l", "b", "backward", "left", "right", "right"}; //powinno wrócić do początku
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d test_position = new Vector2d(2,2);
        Animal a = new Animal(map, test_position);
        MoveDirection[] direction = OptionsParser.parse(args);
        Assertions.assertEquals(MoveDirection.LEFT,direction[6]);
        for (MoveDirection x:direction) {
            a.move(x);
        }
        Assertions.assertTrue(a.isAt(test_position));
        Assertions.assertEquals("E", a.toString());
    }

    @Test
    void wrongInputTest() {
        String[] args = {"f", "k"};
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {OptionsParser.parse(args);});
        Assertions.assertEquals("'k' is not legal move specification", exception.getMessage());
        String[] args2 = {"right", "wrongstring", "f", "left", "anotherone"};
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {OptionsParser.parse(args2);});
        Assertions.assertEquals("'wrongstring' is not legal move specification", exception2.getMessage());
    }

    @Test
    void advancedTest() {
        IWorldMap map = new RectangularMap(5,5);
        Animal a = new Animal(map);
        String[] args1 = {"r", "f", "f", "f", "f", "f"};
        MoveDirection[] direction1 = OptionsParser.parse(args1);
        String[] args2 = {"r", "b", "r", "f"};
        MoveDirection[] direction2 = OptionsParser.parse(args2);
        for (MoveDirection x:direction1) {
            a.move(x);
        }
        Assertions.assertTrue(a.isAt(new Vector2d(4, 2)));
        for (MoveDirection x:direction1) {
            a.move(x);
        }
        Assertions.assertTrue(a.isAt(new Vector2d(4, 0)));
        for (MoveDirection x:direction1) {
            a.move(x);
        }
        Assertions.assertTrue(a.isAt(new Vector2d(0, 0)));
        for (MoveDirection x:direction1) {
            a.move(x);
        }
        Assertions.assertTrue(a.isAt(new Vector2d(0, 4)));
        for (MoveDirection x:direction2) {
            a.move(x);
        }
        Assertions.assertEquals("S", a.toString());
        Assertions.assertEquals(new Vector2d(0, 3), a.getPosition());
        a.move(MoveDirection.LEFT);
        Assertions.assertEquals("E", a.toString());
    }
}
