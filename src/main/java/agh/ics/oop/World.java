package agh.ics.oop;
import static agh.ics.oop.Direction.Commands;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        //String[] coms = {"f", "f", "r", "l", "r"};
        String[] coms = args;
        Commands[] newComs = convert(coms);
        run(newComs);
        System.out.println("system zakończył działanie");
    }

    /*static void run(String[] coms) {
        System.out.println("zwierzak idzie do przodu");
        for (int i=0; i< coms.length; i++) {
            System.out.print(coms[i]);
            if (i != coms.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }*/

    /*static void run(String[] coms) {
        for (int i=0; i< coms.length; i++) {
            switch(coms[i]) {
                case "f":
                    System.out.println("zwierzak idzie do przodu");
                    break;
                case "b":
                    System.out.println("zwierzak idzie do tyłu");
                    break;
                case "l":
                    System.out.println("zwierzak idzie w lewo");
                    break;
                case "r":
                    System.out.println("zwierzak idzie w prawo");
                    break;
            }
        }
    }*/

    static void run(Commands[] coms) {
        //for (int i = 0; i < coms.length; i++) {
        for (Commands i : coms) {
            if (i == null) {
                continue;
            }
            switch (i) {
                case F:
                    System.out.println("zwierzak idzie do przodu");
                    break;
                case B:
                    System.out.println("zwierzak idzie do tyłu");
                    break;
                case L:
                    System.out.println("zwierzak idzie w lewo");
                    break;
                case R:
                    System.out.println("zwierzak idzie w prawo");
                    break;
                default:
                    break;
            }
        }
    }

    static Commands[] convert(String[] coms) {
        Commands[] listofComs = new Commands[coms.length];
        for (int i = 0; i < coms.length; i++) {
            switch(coms[i]) {
                case "f":
                    listofComs[i] = Commands.F;
                    break;
                case "b":
                    listofComs[i] = Commands.B;
                    break;
                case "l":
                    listofComs[i] = Commands.L;
                    break;
                case "r":
                    listofComs[i] = Commands.R;
            }
        }
        return listofComs;
    }
}
