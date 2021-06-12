package pl.zzpwj;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Setter
@Getter
@Builder
public class Point {
    private Integer id;
    private String name;
    private float longitude;
    private float latitude;
}

