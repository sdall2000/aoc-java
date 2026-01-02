package yr2025.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Factory {
    public long part1(List<String> lines) {
        var result = 0L;

        // Sample: [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}

        Pattern pattern = Pattern.compile("\\[.*?\\]");
        Pattern digits = Pattern.compile("\\((\\d+(?:,\\d+)*)\\)");

        for (String machineLine : lines) {
            System.out.println(machineLine);
            Matcher matcher = pattern.matcher(machineLine);
            Matcher digitsMatcher = digits.matcher(machineLine);

            if (!matcher.find()) return -1;

            String endStateString = matcher.group(0);

            int actualLength = endStateString.length() - 2;

            boolean[] initialState = new boolean[actualLength];
            boolean[] goalState = new boolean[actualLength];

            for (int i=1; i < endStateString.length()-1; i++) {
                initialState[i-1] = false;
                goalState[i-1] = endStateString.charAt(i) == '#';
            }

            List<Boolean> buttons = new ArrayList<>();

            while (digitsMatcher.find()) {
                for (int i=1; i <= digitsMatcher.groupCount(); i++) {
                    System.out.println(digitsMatcher.group(i));
                }
            }

            System.out.println();

            Machine machine = new Machine(initialState, goalState, null, null);
        }

        return result;
    }

    public long part2(List<String> lines) {
        var result = 0L;

        return result;
    }

    private class Machine {
        private boolean[] currentState;
        private boolean[] goalState;
        private List<boolean[]> buttons;
        private int[] joltageRequirements;

        public Machine(
            boolean[] currentState,
            boolean[] goalState,
            List<boolean[]> buttons,
            int[] joltageRequirements) {
            this.currentState = currentState;
            this.goalState = goalState;
            this.buttons = buttons;
            this.joltageRequirements = joltageRequirements;
        }
    }
}