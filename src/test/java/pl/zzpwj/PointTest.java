package pl.zzpwj;

import junit.framework.TestCase;

public class PointTest extends TestCase {

    public void ttest() {
        Point point = Point.builder()
                .id(0)
                .name("Test")
                .longitude(1.0f)
                .latitude(1.0f)
                .build();
    }
}