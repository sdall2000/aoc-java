package yr2025.day09;

import common.Utilities;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTheaterTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day09/input.txt");
        var solution = new MovieTheater();

        // 2147461318 too low

        assertEquals(4_771_532_800L, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day09/sample.txt");
        var solution = new MovieTheater();

        // 4771532800 too high

        assertEquals(-1, solution.part2(lines));
    }

    @Test
    void part3() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day09/sample.txt");
        var solution = new MovieTheater();

        assertEquals(24, solution.part3(lines));
    }
}