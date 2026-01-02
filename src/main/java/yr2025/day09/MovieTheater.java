package yr2025.day09;

import java.util.ArrayList;
import java.util.List;

import common.RowCol;
import common.RowColShape;

public class MovieTheater {
    public long part1(List<String> lines) {
        var result = 0L;

        List<RowCol> coordinates = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split(",");

            RowCol rowCol = new RowCol(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

            coordinates.add(rowCol);
        }

        // Now calculate the area of all pairs

        for (RowCol coord1 : coordinates) {
            for (RowCol coord2 : coordinates) {
                long rows = Math.abs(coord1.row() - coord2.row()) + 1;
                long cols = Math.abs(coord1.col() - coord2.col()) + 1;

                result = Math.max(result, rows * cols);
            }
        }

        return result;
    }

    public long part2(List<String> lines) {
        var largest = 0L;

        List<RowCol> coordinates = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split(",");

            RowCol rowCol = new RowCol(Integer.parseInt(split[1]), Integer.parseInt(split[0]));

            coordinates.add(rowCol);
        }

        RowColShape shape = new RowColShape(coordinates);

        // Now calculate the area of all pairs

        for (int i=0; i < coordinates.size() - 1; i++) {
            var coord1 = coordinates.get(i);
            for (int j=i+1; j < coordinates.size(); j++) {
                var coord2 = coordinates.get(j);

                long rows = Math.abs(coord1.row() - coord2.row()) + 1;
                long cols = Math.abs(coord1.col() - coord2.col()) + 1;

                long area = rows * cols;

                if (area < largest) continue;

                int minCol = Math.min(coord1.col(), coord2.col());
                int maxCol = Math.max(coord1.col(), coord2.col());
                int minRow = Math.min(coord1.row(), coord2.row());
                int maxRow = Math.max(coord1.row(), coord2.row());

                // Calculate the two opposite points in the rectangle
                RowCol upperPoint = new RowCol(minRow, minCol);
                if (coord1.equals(upperPoint) || coord2.equals(upperPoint)) {
                    upperPoint = new RowCol(minRow, maxCol);
                }

                RowCol lowerPoint = new RowCol(maxRow, minCol);
                if (coord1.equals(lowerPoint) || coord2.equals(lowerPoint)) {
                    lowerPoint = new RowCol(maxRow, maxCol);
                }

                // Now, see if upperPoint and lowerPoint are in the shape.
                if (shape.isPointInShape(upperPoint) && shape.isPointInShape(lowerPoint)) largest = Math.max(largest, area);
            }
        }

        return largest;
    }

    public long part3(List<String> lines) {
        var largest = 0L;

        List<RowCol> coordinates = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split(",");

            RowCol rowCol = new RowCol(Integer.parseInt(split[1]), Integer.parseInt(split[0]));

            coordinates.add(rowCol);
        }

        RowColShape shape = new RowColShape(coordinates);

        // Now calculate the area of all pairs

        var coord1 = new RowCol(5, 9);
        var coord2 = new RowCol(3, 2);

        boolean exclude=false;

        int minCol = Math.min(coord1.col(), coord2.col());
        int maxCol = Math.max(coord1.col(), coord2.col());
        int minRow = Math.min(coord1.row(), coord2.row());
        int maxRow = Math.max(coord1.row(), coord2.row());

        if (!shape.isPointInShape(new RowCol(3, 2))) {
            System.out.println("What?");
        }

        for (int row=minRow; row <= maxRow; row++) {
            if (exclude) break;
            for (int col=minCol; col <= maxCol; col++) {
                if (!shape.isPointInShape(new RowCol(row, col))) {
                    System.out.println("Point (" + row + "," + col + ") is not in shape");
                    exclude = true;
                    break;
                }
            }
        }

        if (!exclude) {
            long rows = Math.abs(coord1.row() - coord2.row()) + 1;
            long cols = Math.abs(coord1.col() - coord2.col()) + 1;

            largest = Math.max(largest, rows * cols);
        }

        return largest;
    }
}