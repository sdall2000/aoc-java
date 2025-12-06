package yr2024.day03;

import common.Utilities;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MullItOverTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/yr2024/day03/input.txt");
        var solution = new MullItOver();

        assertEquals(-1, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/yr2024/day03/input.txt");
        var solution = new MullItOver();

        assertEquals(-1, solution.part2(lines));
    }
}