package pl.zzpwj.data;

import java.util.ArrayList;
import lombok.*;

@Getter
public class PointContainer
{
    ArrayList<Point> points = new ArrayList<>();

    public Point getPointById(Integer id) {
        for (Point p : points) {
            if(p.getId().equals(id)) {
                return p;
            }
        }
        return new Point.NullPoint();
    }

    public ArrayList<Point> getPointByName(String name) {
         ArrayList<Point> retPoints = new ArrayList<>();
         for (Point p : points) {
            if(p.getName().equals(name)) {
                retPoints.add(p);
            }
        }
        return retPoints;
    }

    public boolean addPoint(Point point) {
        if(getPointById(point.getId()).getClass() == Point.NullPoint.class) {
            points.add(point);
            return true;
        }
        return false;
    }

    public boolean addPoint(ArrayList<Point> points) {
        boolean result = true;
        for(Point point : points) {
            result = result && addPoint(point);
        }
        if(!result) {
            points.clear();
            return false;
        }
        return true;
    }

}
