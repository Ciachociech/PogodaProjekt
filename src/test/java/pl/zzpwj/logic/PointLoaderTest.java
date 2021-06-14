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

        Assert.assertEquals(plPoints.get(0).getId(), Integer.valueOf(752963));
        Assert.assertEquals(plPoints.get(0).getName(), "Żyrzyn");
        Assert.assertEquals(22.0917f, plPoints.get(0).getLongitude(), 0.01);
        Assert.assertEquals(51.49918f, plPoints.get(0).getLatitude(), 0.01);

        Assert.assertEquals(plPoints.get(1).getId(), Integer.valueOf(752967));
        Assert.assertEquals(plPoints.get(1).getName(), "Żyrardów");
        Assert.assertEquals(20.44599f, plPoints.get(1).getLongitude(), 0.01);
        Assert.assertEquals(52.048801f, plPoints.get(1).getLatitude(), 0.01);
    }
}
