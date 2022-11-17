package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        try {
            IWorldMap map = new GrassField(10);
            MoveDirection[] direction = new OptionsParser().parse(args);
            Vector2d[] position = {new Vector2d(4, 0), new Vector2d(10,10)};
            System.out.println(map.toString());
            SimulationEngine engine = new SimulationEngine(direction, map, position);
            engine.run();
            System.out.println(map.toString());
        } catch (IllegalArgumentException e){
            System.out.println("incorrect input, " + e.getMessage());
        }
    }

}