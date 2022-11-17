package agh.ics.oop;
import com.sun.source.tree.Tree;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;

public class MapBoundary implements IPositionChangeObserver {
    //interfejs comparable, comparator, sorted set

    Comparator<Vector2d> compX = new Comparator<Vector2d>() {
        public int compare(Vector2d a, Vector2d b) {
            if (a.x < b.x) {return -1;}
            if (a.x > b.x) {return 1;}
            return (int) Math.signum(a.y - b.y); // jeżeli tu pojawi się zero to coś poszło mocno nie tak
            // ale nie powinno, współrzędne są unikalne dla zwierząt (w końcu są używane jako klucz)
        }
    };

    Comparator<Vector2d> compY = new Comparator<Vector2d>() {
        @Override
        public int compare(Vector2d a, Vector2d b) {
            if (a.y < b.y) {return -1;}
            if (a.y > b.y) {return 1;}
            return (int) Math.signum(a.x - b.x);
        }
    };
    TreeSet<Vector2d> setX = new TreeSet<>(compX);
    TreeSet<Vector2d> setY = new TreeSet<>(compY);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        setX.remove(oldPosition);
        setX.add(newPosition);
        setY.remove(oldPosition);
        setY.add(newPosition);
    }

    public void addElement(Vector2d position) {
        setX.add(position);
        setY.add(position);
    }

    public Vector2d getUpperBoundaries() {
        return setX.last().upperRight(setY.last());
    }

    public Vector2d getLowerBoundaries() {
        return setX.first().lowerLeft(setY.first());
    }

//    TreeSet<IMapElement> setX = new TreeSet<>(new MapElementComparatorX());
//    TreeSet<IMapElement> setY = new TreeSet<>(new MapElementComparatorY());
//
//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        setX.add(super.objectAt(newPosition))
//    }
}
