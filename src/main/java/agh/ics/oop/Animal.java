//implementacja mechanizmu: może zrobić klasę Map gdzie będzie jakiś array 5x5 który symbolizuje pola naszej mapy
//i ma wartości true/false (default false, co oznacza że nie ma tam zwierzęcia) i przy przesuwaniu któregoś zwierzęcia
//najpierw sprawdza się czy docelowe pole jest wolne (ma wartość false), potem zmienia odpowiednie wartości
//alt, prościej: jak są tylko dwa zwierzęta i znamy ich nazwy to można używać metody isAt przed zmianą położenia
//konkretniej, np: policzyć gdzie idzie zwierzę A, zapytać zwierzę B czy tam jest, jak nie to wykonujemy ruch

package agh.ics.oop;

public class Animal {

    private MapDirection dir = MapDirection.NORTH;
    private Vector2d pos = new Vector2d(2, 2);

    @Override
    public String toString() {
        return "position: " + this.pos.toString() + ", direction: " + this.dir.toString();
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
                if (newField.precedes(new Vector2d(4, 4)) && newField.follows(new Vector2d(0, 0))) {
                    this.pos = newField;
                }
            }
            case FORWARD -> {
                newField = this.pos.add(this.dir.toUnitVector());
                if (newField.precedes(new Vector2d(4, 4)) && newField.follows(new Vector2d(0, 0))) {
                    this.pos = newField;
                }
            }
            default -> {
                break;
            }
        }
    }

}
