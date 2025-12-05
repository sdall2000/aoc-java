package day05;

import common.Pair;

import java.util.ArrayList;
import java.util.List;

public class Cafeteria {
    public long part1(List<String> lines) {
        var result = 0L;

        List<Pair<Long, Long>> pairs = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) continue;

            if (line.contains("-")) {
                String[] split = line.split("-");

                pairs.add(new Pair<>(Long.parseLong(split[0]), Long.parseLong(split[1])));
            } else {
                long ingredient = Long.parseLong(line);

                boolean contains = false;

                for (var range : pairs) {
                    if (range.value0() <= ingredient && range.value1() >= ingredient) {
                        result++;
                        break;
                    }
                }
            }
        }

        return result;
    }

    public long part2(List<String> lines) {
        var result = 0L;

        ArrayList<Pair<Long, Long>> pairs = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) continue;

            if (line.contains("-")) {
                String split[] = line.split("-");

                long start = Long.parseLong(split[0]);
                long stop = Long.parseLong(split[1]);

                // Loop through existing pairs and find overlapping ranges
                for (int i=0; i < pairs.size(); i++) {
                    Pair<Long, Long> current = pairs.get(i);

                    if (current == null) continue;

                    if (start <= current.value1() && stop >= current.value0()) {
                        start = Math.min(start, current.value0());
                        stop = Math.max(stop, current.value1());

                        pairs.set(i, null);
                    }
                }

                pairs.add(new Pair<>(start, stop));
            }
        }

        for (var pair : pairs) {
            if (pair == null) continue;
            result += (pair.value1() - pair.value0() + 1);
        }

        return result;
    }
}