package yr2025.day03;

import java.util.List;

public class Lobby {
    public long getMaxJoltage(List<String> lines, int batteriesAvailable) {
        var result = 0L;

        for (String line : lines) {
            long[] batteries = new long[line.length()];

            for (int i=0; i < line.length(); i++) {
                batteries[i] = Long.parseLong(String.valueOf(line.charAt(i)));
            }

            result += getMaxJoltage(batteries, 0, batteriesAvailable);
        }

        return result;
    }

    // Get the max joltage given the array of batteries, the start index, and the digits remaining
    private long getMaxJoltage(long[] batteries, int startIndex, int digitsRemaining) {
        long maxValue = -1;
        int maxIndex = -1;

        for (int i=startIndex; i <= batteries.length - digitsRemaining; i++) {
            if (batteries[i] > maxValue) 
            {
                maxValue = batteries[i];
                maxIndex = i;
            }
        }

        if (digitsRemaining == 1) return maxValue;

        digitsRemaining--;

        return maxValue * Math.round(Math.pow(10, digitsRemaining)) + getMaxJoltage(batteries, maxIndex+1, digitsRemaining);
    }

}