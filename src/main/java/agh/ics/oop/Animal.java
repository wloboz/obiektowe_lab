//implementacja mechanizmu: może zrobić klasę Map gdzie będzie jakiś array 5x5 który symbolizuje pola naszej mapy
//i ma wartości true/false (default false, co oznacza że nie ma tam zwierzęcia) i przy przesuwaniu któregoś zwierzęcia
//najpierw sprawdza się czy docelowe pole jest wolne (ma wartość false), potem zmienia odpowiednie wartości
//alt, prościej: jak są tylko dwa zwierzęta i znamy ich nazwy to można używać metody isAt przed zmianą położenia
//konkretniej, np: policzyć gdzie idzie zwierzę A, zapytać zwierzę B czy tam jest, jak nie to wykonujemy ruch

package agh.ics.oop;

public class Animal {

    private MapDirection dir = MapDirection.NORTH;
    private Vector2d pos = new Vector2d(2, 2);

    private IWorldMap map;

    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.pos = initialPosition;
    }

    //getter do pos i dir
    public Vector2d getPosition() {
        return this.pos;
    }

    public MapDirection getDirection() {
        return this.dir;
    }

    @Override
    public String toString() {
        return switch (this.dir) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    boolean isAt(Vector2d position) {
        return this.pos.equals(position);
    }

    void move(MoveDirection direction) {
        if (direction == null) {
            return;
        }
        Vector2d newField = new Vector2d(0,0);
        switch (direction) {
            case RIGHT -> this.dir = this.dir.next();
            case LEFT -> this.dir = this.dir.previous();
            case BACKWARD -> {
                newField = this.pos.subtract(this.dir.toUnitVector());
                if (this.map.canMoveTo(newField) && !this.map.isOccupied(newField)) {
                    this.pos = newField;
                }
            }
            case FORWARD -> {
                newField = this.pos.add(this.dir.toUnitVector());
                if (this.map.canMoveTo(newField) && !this.map.isOccupied(newField)) {
                    this.pos = newField;
                }
            }
            default -> {
                break;
            }
        }
    }




}
