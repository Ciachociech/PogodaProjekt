package pl.zzpwj.logic;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PointLoaderTest {

    PointLoader pointLoader = new PointLoader();

    @Test
    public void PointLoaderReadFile() throws IOException {
        pointLoader.readPointsFromJSON();
        Assert.assertTrue(true);

    }
}
