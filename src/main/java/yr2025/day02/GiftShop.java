package yr2025.day02;

import java.util.List;

public class GiftShop {
    public long part1(List<String> lines) {
        var result = 0L;

        for (String line : lines) {
            if (line.trim().length() > 0) {
                String[] commaSplit = line.split(",");

                for (String c : commaSplit) {
                    String[] range = c.split("-");

                    long start = Long.parseLong(range[0]);
                    long end = Long.parseLong(range[1]);

                    result += getInvalidIdCount(start, end);
                }
            }
        }

        return result;
    }

    public long part2(List<String> lines) {
        var result = 0L;

        for (String line : lines) {
            if (line.trim().length() > 0) {
                String[] commaSplit = line.split(",");

                for (String c : commaSplit) {
                    String[] range = c.split("-");

                    long start = Long.parseLong(range[0]);
                    long end = Long.parseLong(range[1]);

                    result += getInvalidIdCount2(start, end);
                }
            }
        }
        return result;
    }

    public long getInvalidIdCount(long start, long end) {
        long invalidCount = 0;

        for (long n=start; n <= end; n++) {
            String nstr = Long.toString(n);

            if (nstr.length() % 2 != 0) continue;

            String half = nstr.substring(0, nstr.length() / 2);

            if (nstr.equals(half + half)) invalidCount+=n;
        }

        return invalidCount;
    }

    public long getInvalidIdCount2(long start, long end) {
        long invalidCount = 0;

        for (long n=start; n <= end; n++) {
            if (isInvalid(n)) invalidCount += n;
        }

        return invalidCount;
    }

    public boolean isInvalid(long number) {
        String str = Long.toString(number);

        // If four digits, we want to check Strings built from indices 0,0 up to 0,1
        // If five digits, same
        int lastCharIndex = str.length() / 2 - 1;

        // This loop will build substrings that start at index 0, and go up to lastCharIndex
        for (int i=0; i <= lastCharIndex; i++) {
            String s = str.substring(0, i+1);
            int sLength = s.length();

            if (str.length() % s.length() != 0) continue;

            boolean breakOut = false;

            // Now walk through the rest of the string
            for (int j=sLength; j < str.length(); j += sLength) {
                if (!str.substring(j, j + s.length()).equals(s)) {
                    breakOut = true;
                    break;
                }
            }

            if (!breakOut) return true;
        }

        return false;
    }
}