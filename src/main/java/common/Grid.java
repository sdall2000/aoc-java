package common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grid
{
    private final int[][] values;
    private final int maxRow;
    private final int maxCol;
    private final int rowCount;
    private final int colCount;

    private final List<RowCol> nsewOffsets = List.of(
            new RowCol(-1, 0),
            new RowCol(1, 0),
            new RowCol(0, 1),
            new RowCol(0, -1));

    private final List<RowCol> allOffsets = List.of(
            new RowCol(-1, 0),
            new RowCol(-1, 1),
            new RowCol(0, 1),
            new RowCol(1, 1),
            new RowCol(1, 0),
            new RowCol(1, -1),
            new RowCol(0, -1),
            new RowCol(-1, -1));

    public Grid(int rowCount, int colCount) {
        values = new int[rowCount][colCount];

        this.rowCount = rowCount;
        this.colCount = colCount;

        maxRow = rowCount - 1;
        maxCol = colCount - 1;
    }

    public Grid(List<String> lines) {
        // Assumes comma delimited
        this(lines.size(), lines.getFirst().length());

        for (int row=0; row < lines.size(); row++) {
            String line = lines.get(row);

            for (int col=0; col < lines.get(row).length(); col++) {
                values[row][col] = Integer.parseInt(Character.toString(line.charAt(col)));
            }
        }
    }

    public int valueAt(int row, int col) {
        return values[row][col];
    }

    public boolean inBounds(int row, int col) {
        return row >= 0 && row <= maxRow && col >= 0 && col <= maxCol;
    }

    public int[][] getValues()
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

        nsewOffsets.forEach(p -> {
            int rowOffset = p.row();
            int colOffset = p.col();

            int newRow = row + rowOffset;
            int newCol = col + colOffset;

            if (inBounds(newRow, newCol)) list.add(new RowCol(newRow, newCol));
        });

        return list;
    }

    public List<RowCol> getAllValidNeighbors(int row, int col) {
        return allOffsets.stream().filter(p -> {
            int rowOffset = p.row();
            int colOffset = p.col();

            int newRow = row + rowOffset;
            int newCol = col + colOffset;

            return inBounds(newRow, newCol);
        }).collect(Collectors.toList());
    }

    public List<RowColValue> getRowColValues() {
        List<RowColValue> list = new ArrayList<>();

        for (int row=0; row <= maxRow; row++) {
            for (int col=0; col <= maxCol; col++) {
                list.add(new RowColValue(row, col, valueAt(row, col)));
            }
        }

        return list;
    }
}
