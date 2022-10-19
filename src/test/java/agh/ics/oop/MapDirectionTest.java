package agh.ics.oop;

import org.junit.jupiter.api.*;


public class MapDirectionTest {

    @Test
    @DisplayName("test next")
    void nextTest() {
        MapDirection testDirection = MapDirection.EAST;
        testDirection = testDirection.next();
        Assertions.assertEquals(MapDirection.SOUTH, testDirection, "wrong next");
        testDirection = testDirection.next();
        Assertions.assertEquals(MapDirection.WEST, testDirection, "wrong next");
        testDirection = testDirection.next();
        Assertions.assertEquals(MapDirection.NORTH, testDirection, "wrong next");
        testDirection = testDirection.next();
        Assertions.assertEquals(MapDirection.EAST, testDirection, "wrong next");
    }

    @Test
    @DisplayName("test previous")
    void previousTest() {
        MapDirection testDirection = MapDirection.EAST;
        testDirection = testDirection.previous();
        Assertions.assertEquals(MapDirection.NORTH, testDirection, "wrong prev");
        testDirection = testDirection.previous();
        Assertions.assertEquals(MapDirection.WEST, testDirection, "wrong prev");
        testDirection = testDirection.previous();
        Assertions.assertEquals(MapDirection.SOUTH, testDirection, "wrong prev");
        testDirection = testDirection.previous();
        Assertions.assertEquals(MapDirection.EAST, testDirection, "wrong prev");
    }
}
