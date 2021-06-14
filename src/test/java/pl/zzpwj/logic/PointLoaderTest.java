package pl.zzpwj.logic;

import org.junit.Assert;
import org.junit.Test;
import pl.zzpwj.data.Point;

import java.io.IOException;
import java.util.ArrayList;

public class PointLoaderTest {

    PointLoader pointLoader = new PointLoader();

    @Test
    public void PointLoaderReadFile() throws IOException {
        ArrayList<Point> plPoints = pointLoader.readPointsFromJSON();
        Assert.assertEquals(4100, plPoints.size());
    }
}
