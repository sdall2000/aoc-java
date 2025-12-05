package day01;

import common.Pair;

import java.util.List;

public class SecretEntrance {
    public long part1(List<String> lines) {
        var result = 0L;

        var startingNumber = 50;

        for (String l : lines) {
            char direction = l.charAt(0);
            int moves = Integer.parseInt(l.substring(1));

            if (direction == 'L') moves = -moves;

            startingNumber += moves;

            startingNumber %= 100;

            if (startingNumber == 0) result++;
        };

        return result;
    }

    public long part2(List<String> lines) {
        var result = 0L;

        var startingNumber = 50;

        for (String l : lines) {
            char direction = l.charAt(0);
            int moves = Integer.parseInt(l.substring(1));

            Pair<Integer, Integer> pair = turn(startingNumber, direction, moves);

            startingNumber = pair.value0();
            result += pair.value1();
        };

        return result;
    }

    public Pair<Integer, Integer> turn(int startingPosition, char direction, int moves) {
        int touchedZero = 0;

        int revolutions = moves / 100;
        moves = moves % 100;

        if (direction == 'L') {
            moves = -moves;
        }

        int originalStartingPosition = startingPosition;

        startingPosition += moves;

        if (moves != 0) {
            if (startingPosition == 0) touchedZero++;
            if (startingPosition < 0 && originalStartingPosition > 0) touchedZero++;
            if (startingPosition > 99) touchedZero++;
        }

        touchedZero += revolutions;

        if (startingPosition > 99) {
            startingPosition -= 100;
        }

        if(startingPosition < 0) {
            startingPosition += 100;
        }

        return new Pair<Integer, Integer>(startingPosition, touchedZero);
    }
}