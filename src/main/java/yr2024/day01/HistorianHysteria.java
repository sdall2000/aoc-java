package yr2024.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HistorianHysteria {
    public long part1(List<String> lines) {
        var result = 0L;

        List<Long> leftList = new ArrayList<>();
        List<Long> rightList = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split("\\s+");

            leftList.add(Long.parseLong(split[0]));
            rightList.add(Long.parseLong(split[1]));
        }

        leftList.sort(Long::compare);
        rightList.sort(Long::compare);

        for (int i=0; i < leftList.size(); i++) {
            result += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return result;
    }

    public long part2(List<String> lines) {
        var result = 0L;


        List<Long> leftList = new ArrayList<>();
        List<Long> rightList = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split("\\s+");

            leftList.add(Long.parseLong(split[0]));
            rightList.add(Long.parseLong(split[1]));
        }

        Map<Long, Long> counts = rightList
            .stream()
              .collect(Collectors.groupingBy(
                      Function.identity(),
                      Collectors.counting()
              ));

        return leftList
              .stream()
              .mapToLong(v -> v * counts.getOrDefault(v, 0L))
              .sum();
    }
}