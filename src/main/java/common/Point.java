package common;

public record Point(int row, int col) {
    public Point offset(Offset offset) {
        return new Point(row + offset.rowOffset(), col + offset.columnOffset());
    }

    public Offset subtract(Point other) {
        return new Offset(row - other.row(), col - other.col());
    }
}
