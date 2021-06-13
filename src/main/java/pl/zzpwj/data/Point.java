package pl.zzpwj.data;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Builder
@EqualsAndHashCode
public class Point {
    private Integer id;
    private String name;
    private float longitude;
    private float latitude;
}

