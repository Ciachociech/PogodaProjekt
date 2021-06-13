package pl.zzpwj.data;

import org.junit.Assert;
import org.junit.Test;

public class PointContainerTest {

    @Test
    public void pointContainerGenerating() {
        PointContainer pointContainer = new PointContainer();
        Assert.assertEquals(0, pointContainer.getPoints().size());
    }

    @Test
    public void pointContainerAdding() {
        PointContainer pointContainer = new PointContainer();
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();
        Point point1 = Point.builder().id(1).name("Test1").longitude(1.1f).latitude(1.1f).build();
        Point point2 = Point.builder().id(2).name("Test2").longitude(1.2f).latitude(1.2f).build();
        Point point3 = Point.builder().id(1).name("Test3").longitude(1.3f).latitude(1.3f).build();

        Assert.assertEquals(0, pointContainer.getPoints().size());
        Assert.assertTrue(pointContainer.addPoint(point));
        Assert.assertEquals(1, pointContainer.getPoints().size());
        Assert.assertTrue(pointContainer.addPoint(point1));
        Assert.assertEquals(2, pointContainer.getPoints().size());
        Assert.assertTrue(pointContainer.addPoint(point2));
        Assert.assertEquals(3, pointContainer.getPoints().size());
        Assert.assertFalse(pointContainer.addPoint(point3));
        Assert.assertEquals(3, pointContainer.getPoints().size());

        Assert.assertTrue(pointContainer.addPoint(Point.builder().id(3).name("Test3").longitude(1.3f).latitude(1.3f).build()));
        Assert.assertEquals(4, pointContainer.getPoints().size());
    }

    @Test
    public void pointContainerSearching() {
        PointContainer pointContainer = new PointContainer();
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();
        Point point1 = Point.builder().id(1).name("Test1").longitude(1.1f).latitude(1.1f).build();

        Assert.assertTrue(pointContainer.addPoint(point));
        Assert.assertTrue(pointContainer.addPoint(point1));
        Assert.assertTrue(pointContainer.addPoint(Point.builder().id(2).name("Test2").longitude(1.2f).latitude(1.2f).build()));
        Assert.assertTrue(pointContainer.addPoint(Point.builder().id(3).name("Test3").longitude(1.3f).latitude(1.3f).build()));
        Assert.assertEquals(4, pointContainer.getPoints().size());

        Assert.assertEquals(pointContainer.getPointById(0), point);
        Assert.assertEquals(pointContainer.getPointById(1), point1);
        Assert.assertNull(pointContainer.getPointById(4));

        Assert.assertEquals(pointContainer.getPointByName("Test"), point);
        Assert.assertEquals(pointContainer.getPointByName("Test1"), point1);
        Assert.assertNull(pointContainer.getPointByName("TestX"));
    }
}
