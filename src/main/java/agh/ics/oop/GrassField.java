package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    int grassFields;
    ArrayList<Grass> grassList = new ArrayList<>();
    Vector2d lowerGrassBound, upperGrassBound;

    private void bounds(int n) {
        this.lowerGrassBound = new Vector2d(0,0);
        this.upperGrassBound = new Vector2d((int)Math.sqrt(n*10), (int)Math.sqrt(n*10));
    }

    public GrassField(int grassFields) {
        this.grassFields = grassFields;
        bounds(grassFields);
        this.upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.lowerLeft = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
        for (int i = 0; i < grassFields; i++) {
            createGrass();
        }
    }

    private void createGrass() {  // tworzy 1 trawę
        int min = 0;
        int max = this.upperGrassBound.x;
        int x, y;
        Vector2d newLocation;
        Random rand = new Random();
        rand.setSeed(100);
        do {
            x = rand.nextInt(max);
            y = rand.nextInt(max);
            newLocation = new Vector2d(x, y);
        } while (isOccupied(newLocation));
        grassList.add(new Grass(newLocation));
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) {return true;}
        for (Grass i: this.grassList) {
            if (position.equals(i.position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {  // ma zwracać trawę lub zwierzę, zwierzę ważniejsze
        for (Animal i: this.animalList) {
            if (i.isAt(position)) {
                return i;
            }
        }
        for (Grass i: this.grassList) {
            if (position.equals(i.position)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void setPrintBounds() {
        if (!animalList.isEmpty()) {
            printLowerLeft = printUpperRight = animalList.get(0).getPosition();
        } else if (!grassList.isEmpty()) {
            printLowerLeft = printUpperRight = grassList.get(0).getPosition();
        } else {
            printLowerLeft = new Vector2d(0,0);
            printUpperRight = new Vector2d(0,0);
            return;
        }
        for (Animal i: animalList) {
            printLowerLeft = printLowerLeft.lowerLeft(i.getPosition());
            printUpperRight = printUpperRight.upperRight(i.getPosition());
        }
        for (Grass i: grassList) {
            printLowerLeft = printLowerLeft.lowerLeft(i.getPosition());
            printUpperRight = printUpperRight.upperRight(i.getPosition());
        }
    }


}
