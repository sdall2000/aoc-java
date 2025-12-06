package yr2025.day02;

import common.Utilities;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GiftShopTest {
    // @Test
    void part1() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day02/input.txt");
        var solution = new GiftShop();

        assertEquals(13_108_371_860L, solution.part1(lines));
    }

    @Test
    void part2() throws IOException {
        var lines = Utilities.readResourceLines("/yr2025/day02/input.txt");
        var solution = new GiftShop();

        // 1268158550469635 not correct

        assertEquals(22_471_660_255L, solution.part2(lines));
    }

    @Test
    void invalidTest() {
        var solution = new GiftShop();

        assertFalse(solution.isInvalid(223));
        assertTrue(solution.isInvalid(1188511885));
        assertFalse(solution.isInvalid(1));
        assertTrue(solution.isInvalid(123123123));
        assertTrue(solution.isInvalid(11));
        assertTrue(solution.isInvalid(222));
    }
}