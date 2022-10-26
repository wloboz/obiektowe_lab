package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal saba = new Animal();
        MoveDirection[] direction = OptionsParser.parse(args);
        for (MoveDirection x:direction) {
            saba.move(x);
        }
        System.out.println(saba.toString());
    }

}