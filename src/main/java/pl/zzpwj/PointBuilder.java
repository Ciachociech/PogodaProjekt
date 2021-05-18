package pl.zzpwj;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PointBuilder {
    private Integer id;
    private String name;
    private float longitude;
    private float latitude;
}
