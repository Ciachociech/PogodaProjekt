package pl.zzpwj;

import lombok.*;

@AllArgsConstructor(access=AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
@Builder
public class Point {
    private Integer id;
    private String name;
    private float longitude;
    private float latitude;
}

