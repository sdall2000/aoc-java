package yr2025.day07;

import common.RowCol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Laboratories {
    public long part1(List<String> lines) {
        var splits = 0L;

        Set<Integer> beams = new HashSet<>();

        for (String line : lines) {
            Set<Integer> newBeams = new HashSet<>();
            if (beams.isEmpty()) {
                // Find the starting point
                beams.add(line.indexOf('S'));
            } else {
                // go through each instance of '^' and see if we have a beam there
                for (int b : beams) {
                    if (line.charAt(b) == '^') {
                        splits++;
                        newBeams.add(b-1);
                        newBeams.add(b+1);
                    } else {
                        newBeams.add(b);
                    }
                }

                beams = newBeams;
            }
        }

        return splits;
    }

    public long part2(List<String> lines) {
        Map<Integer, Long> particleXToCount = new HashMap<>();

        for (String line : lines) {
            Map<Integer, Long> newParticleXToCount = new HashMap<>();
            if (particleXToCount.isEmpty()) {
                // Find the starting point
                particleXToCount.put(line.indexOf('S'), 1L);
            } else {
                // go through each instance of '^' and see if we have a beam there
                for (var e : particleXToCount.entrySet()) {
                    if (line.charAt(e.getKey()) == '^') {
                        updateMap(newParticleXToCount, e.getKey()-1, e.getValue());
                        updateMap(newParticleXToCount, e.getKey()+1, e.getValue());
                    } else {
                        updateMap(newParticleXToCount, e.getKey(), e.getValue());
                    }
                }

                particleXToCount = newParticleXToCount;
            }
        }

        return particleXToCount.values().stream().mapToLong(Long::longValue).sum();
    }

    private void updateMap(Map<Integer, Long> particleXToCount, int position, long currentCount) {
        particleXToCount.merge(position, currentCount, Long::sum);
    }
}