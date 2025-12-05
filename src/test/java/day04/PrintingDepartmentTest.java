package day04;

import common.Utilities;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrintingDepartmentTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/day04/input.txt");
        var solution = new PrintingDepartment();

        assertEquals(1_363, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/day04/input.txt");
        var solution = new PrintingDepartment();

        assertEquals(8_184, solution.part2(lines));
    }
}