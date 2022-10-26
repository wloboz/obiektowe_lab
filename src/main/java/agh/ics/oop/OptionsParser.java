package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int n = args.length;
        MoveDirection[] result = new MoveDirection[n];
        int j =0;
        for (int i=0; i<n; i++) {
            switch (args[i]) {
                case "f", "forward" -> result[j] = MoveDirection.FORWARD;
                case "b", "backward" -> result[j] = MoveDirection.BACKWARD;
                case "r", "right" -> result[j] = MoveDirection.RIGHT;
                case "l", "left" -> result[j] = MoveDirection.LEFT;
                default -> {continue;}
            }
            j += 1;
        }
        return result;
    }
}
