package yr2025.day01;

import common.Utilities;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecretEntranceTest {
    @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day01/input.txt");
        var solution = new SecretEntrance();

        assertEquals(1_165, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day01/input.txt");
        var solution = new SecretEntrance();

        assertEquals(6496, solution.part2(lines));
    }

    @Test
    void turnsTest() {
        var solution = new SecretEntrance();

        assertEquals(0, solution.turn(0, 'L', 1).value1());
        assertEquals(1, solution.turn(1, 'L', 1).value1());
        assertEquals(1, solution.turn(1, 'L', 2).value1());
        assertEquals(1, solution.turn(99, 'R', 1).value1());
        assertEquals(2, solution.turn(0, 'R', 200).value1());
        assertEquals(2, solution.turn(0, 'L', 200).value1());
    }

}