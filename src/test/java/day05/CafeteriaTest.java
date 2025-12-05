package day05;

import common.Utilities;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CafeteriaTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/day05/input.txt");
        var solution = new Cafeteria();

        assertEquals(770, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/day05/input.txt");
        var solution = new Cafeteria();

        assertEquals(357_674_099_117_260L, solution.part2(lines));
    }
}