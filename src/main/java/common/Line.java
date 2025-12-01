package common;

import org.javatuples.Pair;

public record Line(Point point1, Point point2) {
    public boolean connected(Line other) {
        return commonPoint(other) != null;
    }

    public Point commonPoint(Line other) {
        if (point1.equals(other.point1)) return point1;
        if (point1.equals(other.point2)) return point1;
        if (point2.equals(other.point1)) return point2;
        if (point2.equals(other.point2)) return point2;

        return null;
    }


    public Pair<Point, Point> uncommonPoints(Line other) {
        Point common = commonPoint(other);

        if (common == null) return null;

        Point first = null;
        Point second = null;

        if (!point1.equals(common)) first = point1;
        if (!point2.equals(common)) first = point2;

        if (!other.point1.equals(common)) second = other.point1;
        if (!other.point2.equals(common)) second = other.point2;

        if (first == null || second == null) return null;

        if (first.equals(second)) return null;

        return new Pair<>(point1, point2);
    }

    public static boolean isAdjacentLine(Point point1, Point point2) {
        return (point1.row() == point2.row() && Math.abs(point1.col() - point2.col()) == 1) ||
                (point1.col() == point2.col() && Math.abs(point1.row() - point2.row()) == 1);
    }
}
