package agh.ics.oop;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    @Override
    public String toString() {
        switch(this) {
            case EAST: return "east";
            case WEST: return "west";
            case NORTH: return "north";
            case SOUTH: return "south";
        }
        return null;  // krzyczało na mnie żeby jakiegoś returna tutaj jednak dać, nie widzę potrzeby
        // bo w końcu to enum czyli określone wartości a załatwiam wszystkie w switchcase, ale niech mu będzie
    }

    MapDirection next() {
        switch(this) {
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            case NORTH: return EAST;
        }
        return null;  // jw
    }

    MapDirection previous() {
        switch(this) {
            case EAST: return NORTH;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case NORTH: return WEST;
        }
        return null;  // jw
    }

    Vector2d toUnitVector() {
        switch(this) {
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0, -1);
            case WEST: return new Vector2d(-1, 0);
            case NORTH: return new Vector2d(0, 1);
        }
        return new Vector2d(0,0);
    }
}
