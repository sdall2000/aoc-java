package yr2025.day09;

import java.util.ArrayList;
import java.util.List;

import common.RowCol;

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

            RowCol rowCol = new RowCol(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

            coordinates.add(rowCol);
        }

        // Now calculate the area of all pairs

        for (RowCol coord1 : coordinates) {
            for (RowCol coord2 : coordinates) {
                long rows = Math.abs(coord1.row() - coord2.row()) + 1;
                long cols = Math.abs(coord1.col() - coord2.col()) + 1;

                long area = rows * cols;

                if (area > largest) {
                    if (valid(coord1, coord2, coordinates)) {

                    }
                }
            }
        }

        return largest;
    }
}