package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class GrassField extends AbstractWorldMap {

    int grassFields;
    //ArrayList<Grass> grassList = new ArrayList<>();
    HashMap<Vector2d, Grass> grassList = new HashMap<>();
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
        grassList.put(newLocation, new Grass(newLocation));
        bounds.addElement(newLocation);
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) {return true;}
        return (grassList.get(position) != null);
    }

    @Override
    public Object objectAt(Vector2d position) {  // ma zwracać trawę lub zwierzę, zwierzę ważniejsze
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        return grassList.get(position);
    }

    @Override
    public void setPrintBounds() {
        printUpperRight = bounds.getUpperBoundaries();
        printLowerLeft = bounds.getLowerBoundaries();
    }


}
