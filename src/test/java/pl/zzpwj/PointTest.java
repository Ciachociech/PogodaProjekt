package pl.zzpwj;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void pointBuilderSuccess() {
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();

        Assert.assertEquals(point.getId(), Integer.valueOf(0));
        Assert.assertEquals(point.getName(), "Test");
        Assert.assertEquals(1.0f, point.getLongitude(), 0.0);
        Assert.assertEquals(1.0f, point.getLatitude(), 0.0);
    }

    @Test
    public void pointBuilderSuccess1() {
        int id = 0;
        String name = "Test";
        float longitude = 1.0f;
        float latitude = 1.0f;

        Point point = Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build();

        Assert.assertEquals(point.getId(), Integer.valueOf(id));
        Assert.assertEquals(point.getName(), name);
        Assert.assertEquals(longitude, point.getLongitude(), 0.0);
        Assert.assertEquals(latitude, point.getLatitude(), 0.0);
    }

    @Test
    public void pointBuilderSuccess2() {
        int id = 0;
        String name = "Test";
        float longitude = 1.0f;
        float latitude = 1.0f;

        Assert.assertEquals(Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build().getId(), Integer.valueOf(id));
        Assert.assertEquals(Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build().getName(), name);
        Assert.assertEquals(longitude, Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build().getLongitude(), 0.0);
        Assert.assertEquals(latitude, Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build().getLatitude(), 0.0);
    }

    @Test
    public void pointBuilderSuccess3() {
        Assert.assertEquals(Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build().getId(), Integer.valueOf(0));
        Assert.assertEquals(Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build().getName(), "Test");
        Assert.assertEquals(1.0f, Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build().getLongitude(), 0.0);
        Assert.assertEquals(1.0f, Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build().getLatitude(), 0.0);
    }

    @Test
    public void pointEqualsAndHashCode() {
        int id = 0;
        String name = "Test";
        float longitude = 1.0f;
        float latitude = 1.0f;

        Point point = Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build();

        Assert.assertEquals(point.getId(), Integer.valueOf(id));
        Assert.assertEquals(point.getName(), name);
        Assert.assertEquals(longitude, point.getLongitude(), 0.0);
        Assert.assertEquals(latitude, point.getLatitude(), 0.0);

        Assert.assertTrue(point.equals(Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build()));
        Assert.assertEquals(point.hashCode(), Point.builder().id(id).name(name).longitude(longitude).latitude(latitude).build().hashCode());
        Assert.assertTrue(point.equals(Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build()));
        Assert.assertEquals(point.hashCode(), Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build().hashCode());
    }
}