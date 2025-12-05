package common;

import java.util.ArrayList;
import java.util.List;

public class CharGrid
{
    private final char[][] values;
    private final int maxRow;
    private final int maxCol;
    private final int rowCount;
    private final int colCount;

    public final static RowCol NORTH = new RowCol(-1, 0);
    public final static RowCol SOUTH = new RowCol(1, 0);
    public final static RowCol EAST = new RowCol(0, 1);
    public final static RowCol WEST = new RowCol(0, -1);

    public static final List<RowCol> NSEW_OFFSETS = List.of(
            NORTH,
            SOUTH,
            EAST,
            WEST);

    private final List<RowCol> allOffsets = List.of(
            new RowCol(-1, 0),
            new RowCol(-1, 1),
            new RowCol(0, 1),
            new RowCol(1, 1),
            new RowCol(1, 0),
            new RowCol(1, -1),
            new RowCol(0, -1),
            new RowCol(-1, -1));

    public CharGrid(int rowCount, int colCount) {
        values = new char[rowCount][colCount];

        this.rowCount = rowCount;
        this.colCount = colCount;

        maxRow = rowCount - 1;
        maxCol = colCount - 1;
    }

    public CharGrid(List<String> lines) {
        // Assumes comma delimited
        this(lines.size(), lines.getFirst().length());

        for (int row=0; row < lines.size(); row++) {
            String line = lines.get(row);

            for (int col=0; col < lines.get(row).length(); col++) {
                values[row][col] = line.charAt(col);
            }
        }
    }

    public char valueAt(int row, int col) {
        return values[row][col];
    }

    public void setValue(int row, int col, char value) {
        values[row][col] = value;
    }

    public boolean inBounds(int row, int col) {
        return row >= 0 && row <= maxRow && col >= 0 && col <= maxCol;
    }

    public char[][] getValues()
    {
        return values;
    }

    public int getMaxRow()
    {
        return maxRow;
    }

    public int getMaxCol()
    {
        return maxCol;
    }

    public int getRowCount()
    {
        return rowCount;
    }

    public int getColCount()
    {
        return colCount;
    }

    public List<RowCol> getValidNsewNeighbors(int row, int col) {
        List<RowCol> list = new ArrayList<>();

        NSEW_OFFSETS.forEach(p -> {
            int rowOffset = p.row();
            int colOffset = p.col();

            int newRow = row + rowOffset;
            int newCol = col + colOffset;

            if (inBounds(newRow, newCol)) list.add(new RowCol(newRow, newCol));
        });

        return list;
    }

    public List<RowCol> getAllValidNeighbors(int row, int col) {
        List<RowCol> list = new ArrayList<>();

        allOffsets.forEach(p -> {
            int rowOffset = p.row();
            int colOffset = p.col();

            int newRow = row + rowOffset;
            int newCol = col + colOffset;

            if (inBounds(newRow, newCol)) list.add(new RowCol(newRow, newCol));
        });

        return list;
    }

    public List<RowColValue> getRowColValues() {
        List<RowColValue> list = new ArrayList<>();

        for (int row=0; row <= maxRow; row++) {
            for (int col=0; col <= maxCol; col++) {
                list.add(new RowColValue(row, col, (int) valueAt(row, col)));
            }
        }

        return list;
    }

    public Point positionOf(char c) {
        for (int row=0; row <= maxRow; row++) {
            for (int col=0; col <= maxCol; col++) {
                if (values[row][col] == c) {
                    return new Point(row, col);
                }
            }
        }

        return null;
    }

    public boolean inBounds(Point newPosition) {
        return inBounds(newPosition.row(), newPosition.col());
    }

    public char valueAt(Point newPosition) {
        return valueAt(newPosition.row(), newPosition.col());
    }
}
