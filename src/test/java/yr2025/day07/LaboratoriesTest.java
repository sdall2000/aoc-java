package yr2025.day07;

import common.Utilities;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LaboratoriesTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day07/input.txt");
        var solution = new Laboratories();

        assertEquals(1_626, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day07/input.txt");
        var solution = new Laboratories();

        assertEquals(48_989_920_237_096L, solution.part2(lines));
    }
}