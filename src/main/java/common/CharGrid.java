package common;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CharGrid
{
    private final char[][] values;
    private final int maxRow;
    private final int maxCol;
    private final int rowCount;
    private final int colCount;

    public final static Pair<Integer, Integer> NORTH = new Pair<>(-1, 0);
    public final static Pair<Integer, Integer> SOUTH = new Pair<>(1, 0);
    public final static Pair<Integer, Integer> EAST = new Pair<>(0, 1);
    public final static Pair<Integer, Integer> WEST = new Pair<>(0, -1);

    public static final List<Pair<Integer, Integer>> NSEW_OFFSETS = List.of(
            NORTH,
            SOUTH,
            EAST,
            WEST);

    private final List<Pair<Integer, Integer>> allOffsets = List.of(
            new Pair<>(-1, 0),
            new Pair<>(-1, 1),
            new Pair<>(0, 1),
            new Pair<>(1, 1),
            new Pair<>(1, 0),
            new Pair<>(1, -1),
            new Pair<>(0, -1),
            new Pair<>(-1, -1));

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

    public List<Pair<Integer, Integer>> getValidNsewNeighbors(int row, int col) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();

        NSEW_OFFSETS.forEach(p -> {
            int rowOffset = p.getValue0();
            int colOffset = p.getValue1();

            int newRow = row + rowOffset;
            int newCol = col + colOffset;

            if (inBounds(newRow, newCol)) list.add(new Pair<>(newRow, newCol));
        });

        return list;
    }

    public List<Pair<Integer, Integer>> getAllValidNeighbors(int row, int col) {
        return allOffsets.stream().filter(p -> {
            int rowOffset = p.getValue0();
            int colOffset = p.getValue1();

            int newRow = row + rowOffset;
            int newCol = col + colOffset;

            return inBounds(newRow, newCol);
        }).collect(Collectors.toList());
    }

    public List<Triplet<Integer, Integer, Integer>> getRowColValues() {
        List<Triplet<Integer, Integer, Integer>> list = new ArrayList<>();

        for (int row=0; row <= maxRow; row++) {
            for (int col=0; col <= maxCol; col++) {
                list.add(new Triplet<>(row, col, (int) valueAt(row, col)));
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
