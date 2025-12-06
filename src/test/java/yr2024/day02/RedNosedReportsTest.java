package yr2024.day02;

import common.Utilities;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedNosedReportsTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/yr2024/day02/input.txt");
        var solution = new RedNosedReports();

        assertEquals(246, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/yr2024/day02/input.txt");
        var solution = new RedNosedReports();

        assertEquals(318, solution.part2(lines));
    }
}