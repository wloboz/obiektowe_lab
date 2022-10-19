package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Vector2dTest {

    @Test
    void equalsTest() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(1, -2);
        Vector2d c = new Vector2d(1, 2);
        Assertions.assertEquals(a, b);
        Assertions.assertNotEquals(a, c);
    }

    @Test
    void toStringTest() {
        Vector2d a = new Vector2d(1,-2);
        Assertions.assertEquals("(1,-2)",a.toString());
    }

    @Test
    void precedesTest() {
        Vector2d a = new Vector2d(1,-2);
        Vector2d b = new Vector2d(2, -2);
        Vector2d c = new Vector2d(0, 3);
        Assertions.assertTrue(a.precedes(b));
        Assertions.assertFalse(a.precedes(c));
        Assertions.assertTrue(a.precedes(a));
    }

    @Test
    void followsTest() {
        Vector2d a = new Vector2d(1,-2);
        Vector2d b = new Vector2d(1, -3);
        Vector2d c = new Vector2d(0, 3);
        Assertions.assertTrue(a.follows(b));
        Assertions.assertFalse(a.follows(c));
        Assertions.assertTrue(a.follows(a));
    }

    @Test
    void upperRightTest() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(3, 1);
        Assertions.assertEquals(new Vector2d(3, 1), a.upperRight(b));
    }

    @Test
    void lowerLeftTest() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(3, 1);
        Assertions.assertEquals(new Vector2d(1, -2), a.lowerLeft(b));
    }

    @Test
    void addTest() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(3, 1);
        Assertions.assertEquals(new Vector2d(4, -1), a.add(b));
    }

    @Test
    void substractTest() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(3, 1);
        Assertions.assertEquals(new Vector2d(-2, -3), a.subtract(b));
    }

    @Test
    void oppositeTest() {
        Vector2d a = new Vector2d(1, -2);
        Assertions.assertEquals(new Vector2d(-1,2), a.opposite());
    }
}
