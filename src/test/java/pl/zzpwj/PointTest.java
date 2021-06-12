package pl.zzpwj;

import junit.framework.TestCase;

public class PointTest extends TestCase {

    public void builderSuccess() {
        Point point = Point.builder()
                .id(0)
                .name("Test")
                .longitude(1.0f)
                .latitude(1.0f)
                .build();

        assertEquals(point.getId(), Integer.valueOf(0));
        assertEquals(point.getName(), "Test");
        assertEquals(point.getLongitude(), 1.0f);
        assertEquals(point.getLatitude(), 1.0f);
    }
}