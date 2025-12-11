package yr2025.day08;

import common.Utilities;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaygroundTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day08/input.txt");
        var solution = new Playground();

        assertEquals(63_920, solution.part1(lines, 1000));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day08/input.txt");
        var solution = new Playground();

        assertEquals(1_026_594_680, solution.part2(lines));
    }
}