package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Utilities {
    public static List<String> readResourceLines(String resourceFile) throws IOException {
        List<String> lines = new ArrayList<>();

        InputStream inputStream = Utilities.class.getResourceAsStream(resourceFile);

        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        for (String line; (line = bufferedReader.readLine()) != null; ) {
            lines.add(line);
        }

        return lines;
    }

    public static int[][] readIntegerTableFromResource(String resourceFile) throws IOException {
        List<String> lines = readResourceLines(resourceFile);

        int[][] intTable = new int[lines.size()][lines.getFirst().length()];

        for (int row=0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col=0; col < lines.size(); col++) {
                intTable[row][col] = Integer.parseInt(Character.toString(line.charAt(col)));
            }
        }

        return intTable;
    }

    public static Grid readGridFromResource(String resourceFile) throws IOException {
        List<String> lines = readResourceLines(resourceFile);

        return new Grid(lines);
    }

    public static boolean inBounds(List<String> lines, int row, int col) {
        if (row < 0 || col < 0) return false;

        if (row >= lines.size()) return false;

        if (col >= lines.get(row).length()) return false;

        return true;
    }

    public static boolean inBounds(int[][] grid, int row, int col)
    {
        if (row < 0 || col < 0) return false;

        if (row >= grid.length) return false;

        if (col >= grid[row].length) return false;

        return true;
    }

    public static char charAt(List<String> lines, int row, int col) {
        return lines.get(row).charAt(col);
    }

    public static char[][] toArray(List<String> lines)
    {
        char[][] rows = new char[lines.size()][lines.get(0).length()];

        for (int row=0; row < lines.size(); row++) {
            rows[row] = lines.get(row).toCharArray();
        }

        return rows;
    }

    public static int gcd(int num1, int num2) {
        if (num2 == 0) return num1;

        return gcd(num2, num1 % num2);
    }

    public static long gcd(long num1, long num2) {
        if (num2 == 0) return num1;

        return gcd(num2, num1 % num2);
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static long lcm(long ... numbers) {
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result *= numbers[i];
        }
        return result;
    }

    public static long lcm2(long ... numbers) {
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }

    public static int valueAt(List<String> lines, int row, int col) {
        return Integer.parseInt(Character.toString(lines.get(row).charAt(col)));
    }

    // Courtesy Baeldung
    public static Optional<DoublePair> calculateIntersectionPoint(
            double m1,
            double b1,
            double m2,
            double b2) {

        if (m1 == m2) {
            return Optional.empty();
        }

        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;

        DoublePair point = new DoublePair(x, y);
        return Optional.of(point);
    }

    private Utilities() { }


}
