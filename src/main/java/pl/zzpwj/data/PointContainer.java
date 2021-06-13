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
        return null;
    }

    public Point getPointByName(String name) {
        for (Point p : points) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public boolean addPoint(Point point) {
        if(getPointById(point.getId()) == null) {
            points.add(point);
            return true;
        }
        return false;
    }

}
