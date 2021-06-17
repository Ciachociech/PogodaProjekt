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

    @ToString
    public static final class NullPoint extends Point {

        public NullPoint() {
            super(0, "", 0.f, 0.f);
        }
    }

    public String toStringNameWithCoords() {
        StringBuilder stringBuilder = new StringBuilder().append("Name: ").append(name).append("\nLongitude: ");
        if(longitude < 0.f) {
            stringBuilder.append(-longitude).append(" W");
        } else {
            stringBuilder.append(longitude).append(" E");
        }
        stringBuilder.append("\nLatitude: ");
        if(latitude < 0.f) {
            stringBuilder.append(-latitude).append(" S");
        } else {
            stringBuilder.append(latitude).append(" N");
        }
        return stringBuilder.append("\n").toString();
    }

    public String toStringNameWithCoordsOneLine() {
        StringBuilder stringBuilder = new StringBuilder().append("Name: ").append(name).append(" (Long.: ");
        if(longitude < 0.f) {
            stringBuilder.append(-longitude).append(" W,");
        } else {
            stringBuilder.append(longitude).append(" E,");
        }
        stringBuilder.append(" Lat.: ");
        if(latitude < 0.f) {
            stringBuilder.append(-latitude).append(" S)");
        } else {
            stringBuilder.append(latitude).append(" N)");
        }
        return stringBuilder.toString();
    }
}

