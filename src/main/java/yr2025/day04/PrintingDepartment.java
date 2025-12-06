package yr2025.day04;

import java.util.List;

import common.CharGrid;

public class PrintingDepartment {
    public long part1(List<String> lines) {
        var result = 0L;

        CharGrid charGrid = new CharGrid(lines);

        for (int r=0; r < charGrid.getRowCount(); r++) {
            for (int c=0; c < charGrid.getColCount(); c++) {
                if (charGrid.valueAt(r, c) == '@') {
                    var neighbors = charGrid.getAllValidNeighbors(r, c);

                    int paperCount = 0;

                    for (var neighbor : neighbors) {
                        if (charGrid.valueAt(neighbor.row(), neighbor.col()) == '@') paperCount++;
                    }

                    if (paperCount < 4) result++;
                }
            }
        }
        
        return result;
    }

    public long part2(List<String> lines) {
        var result = 0L;

        CharGrid charGrid = new CharGrid(lines);

        boolean modifying = true;

        while (modifying) {
            modifying = false;

        for (int r=0; r < charGrid.getRowCount(); r++) {
            for (int c=0; c < charGrid.getColCount(); c++) {
                if (charGrid.valueAt(r, c) == '@') {
                    var neighbors = charGrid.getAllValidNeighbors(r, c);

                    int paperCount = 0;

                    for (var neighbor : neighbors) {
                        if (charGrid.valueAt(neighbor.row(), neighbor.col()) == '@') paperCount++;
                    }

                    if (paperCount < 4) {
                        result++;
                        modifying = true;
                        charGrid.setValue(r, c, '.');
                    }
                }
            }
        }
    }
        
        return result;
    }
}