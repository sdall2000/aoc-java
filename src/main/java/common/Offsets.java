package common;

import java.util.List;
import java.util.Set;

public class Offsets {
    public static final Offset NORTH_OFFSET = new Offset(-1, 0);
    public static final Offset NORTHEAST_OFFSET = new Offset(-1, 1);
    public static final Offset EAST_OFFSET = new Offset(0, 1);
    public static final Offset SOUTHEAST_OFFSET = new Offset(1, 1);
    public static final Offset SOUTH_OFFSET = new Offset(1, 0);
    public static final Offset SOUTHWEST_OFFSET = new Offset(1, -1);
    public static final Offset WEST_OFFSET = new Offset(0, -1);
    public static final Offset NORTHWEST_OFFSET = new Offset(-1, -1);

    public static final Set<Offset> NSEW_OFFSETS = Set.of(NORTH_OFFSET, SOUTH_OFFSET, EAST_OFFSET, WEST_OFFSET);
    public static final Set<Offset> ALL_OFFSETS = Set.of(
            NORTH_OFFSET,
            NORTHEAST_OFFSET,
            NORTHWEST_OFFSET,
            SOUTH_OFFSET,
            SOUTHEAST_OFFSET,
            SOUTHWEST_OFFSET,
            EAST_OFFSET,
            WEST_OFFSET);
}
