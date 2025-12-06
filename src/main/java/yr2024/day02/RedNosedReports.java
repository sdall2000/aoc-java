package yr2024.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RedNosedReports {
    public long part1(List<String> lines) {
        return lines
                .stream()
                .filter(l -> isReportSafe(
                        Arrays.stream(l.split("\\s+"))
                                .map(Long::parseLong)
                                .toList()))
                .count();
    }

    public long part2(List<String> lines) {
        return lines
                .stream()
                .filter(l -> isReportMostlySafe(
                        Arrays.stream(l.split("\\s+"))
                                .map(Long::parseLong)
                                .toList()))
                .count();
    }

    private boolean isReportSafe(Collection<Long> report) {
        Long lastLevel = null;
        Boolean increasing = null;

        for(Long level : report) {
            // Make sure the first number has been established
            if (lastLevel == null) {
                lastLevel = level;
            } else {
                long diff = level - lastLevel;

                // Can't be too far apart
                if (Math.abs(diff) < 1 || Math.abs(diff) > 3) return false;

                // Make sure the increasing flag has been set
                if (increasing == null) {
                    increasing = level > lastLevel;
                }

                if ((increasing && diff < 0) || (!increasing && diff > 0)) return false;

                lastLevel = level;
            }
        }

        return true;
    }

    public boolean isReportMostlySafe(List<Long> report) {
        Long lastLevel = null;
        Boolean increasing = null;

        boolean failed = false;

        for(int i=0; i < report.size(); i++) {
            Long level = report.get(i);

            // Make sure the first number has been established
            if (lastLevel == null) {
                lastLevel = level;
            } else {
                long diff = level - lastLevel;

                // Can't be too far apart
                if (Math.abs(diff) < 1 || Math.abs(diff) > 3) failed = true;

                // Make sure the increasing flag has been set
                if (increasing == null) {
                    increasing = level > lastLevel;
                }

                if ((increasing && diff < 0) || (!increasing && diff > 0)) failed = true;

                lastLevel = level;
            }

            if (failed) {
                // Retry by removing current element that failed
                List<Long> clone1 = new ArrayList<>(report);
                clone1.remove(i);

                // Retry by removing previous element
                List<Long> clone2 = new ArrayList<>(report);
                clone2.remove(i-1);

                // Retry by removing two elements back
                // E.g. 20 21 19 18 17
                List<Long> clone3 = new ArrayList<>(report);

                if (i >= 2) {
                    clone3.remove(i-2);
                }

                return isReportSafe(clone1) || isReportSafe(clone2) || isReportSafe(clone3);
            }
        }

        return true;
    }
}