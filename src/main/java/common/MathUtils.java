package common;

import org.javatuples.Pair;

public class MathUtils {
    // Courtesy ChatGPT
    public static Pair<Double, Double> solveLinearEquations(double a1, double b1, double c1, double a2, double b2, double c2) {
        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            return null; // No unique solution
        }

        double x = (c1 * b2 - c2 * b1) / determinant;
        double y = (a1 * c2 - a2 * c1) / determinant;

        return new Pair<>(x, y);
    }
}
