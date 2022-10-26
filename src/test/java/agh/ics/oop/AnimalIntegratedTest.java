package agh.ics.oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalIntegratedTest {
    @Test
    void setupTest() {
        Animal a = new Animal();
        Vector2d test_position = new Vector2d(2,2);
        Assertions.assertTrue(a.isAt(test_position));
        Assertions.assertEquals("position: (2,2), direction: north", a.toString());
    }

    @Test
    void basicTest() {
        String[] args = {"f", "forward", "r", "l", "b", "k", "backward", "left", "wrongstring", "right"}; //powinno wrócić do początku
        Animal a = new Animal();
        Vector2d test_position = new Vector2d(2,2);
        MoveDirection[] direction = OptionsParser.parse(args);
        Assertions.assertEquals(MoveDirection.LEFT,direction[6]);
        for (MoveDirection x:direction) {
            a.move(x);
        }
        Assertions.assertTrue(a.isAt(test_position));
    }

    @Test
    void advancedTest() {
        Animal a = new Animal();
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
        Assertions.assertEquals("position: (0,3), direction: south", a.toString());
        a.move(MoveDirection.LEFT);
        Assertions.assertEquals("position: (0,3), direction: east", a.toString());
    }
}
