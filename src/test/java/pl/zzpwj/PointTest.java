package pl.zzpwj;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void builderSuccess() {
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();

        Assert.assertEquals(point.getId(), Integer.valueOf(0));
        Assert.assertEquals(point.getName(), "Test");
        Assert.assertEquals(1.0f, point.getLongitude(), 0.0);
        Assert.assertEquals(1.0f, point.getLatitude(), 0.0);
    }
}