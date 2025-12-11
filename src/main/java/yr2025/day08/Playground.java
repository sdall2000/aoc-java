package yr2025.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import common.Pair;
import common.Triplet;

public class Playground {
    public long part1(List<String> lines, int connectionCount) {
        List<Set<Triplet<Integer, Integer, Integer>>> circuits = new ArrayList<>();
        Map<Triplet<Integer, Integer, Integer>, Set<Triplet<Integer, Integer, Integer>>> junctionToCircuit = new HashMap<>();

        Set<Triplet<Integer, Integer, Integer>> coordinates = new HashSet<>();

        for (String line : lines) {
            String[] split = line.split(",");

            coordinates.add(new Triplet<>(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }

        // Create a map of distances to pairs of junction boxes
        TreeMap<Double, Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>> tm = new TreeMap<>();

        for (var junctionBox : coordinates) {
            for (var compareJunctionBox : coordinates) {
                if (junctionBox == compareJunctionBox) continue;

                double d = distance(junctionBox, compareJunctionBox);

                if (tm.containsKey(d)) {
                } else {
                    tm.put(d, new Pair<>(junctionBox, compareJunctionBox));
                }
            }
        }

        int connected = 0;

        for (var pair : tm.values()) {
            var junctionBox = pair.value0();
            var closest = pair.value1();

            if (junctionToCircuit.containsKey(junctionBox) && junctionToCircuit.containsKey(closest)) {
                var junctionBoxCircuit = junctionToCircuit.get(junctionBox);
                var closestCircuit = junctionToCircuit.get(closest);

                // Both junction boxes are already on the same circuit - nothing to do
                if (junctionBoxCircuit != closestCircuit) {
                    // Merge closestCircuit into junctionBoxCircuit
                    junctionBoxCircuit.addAll(closestCircuit);

                    // Now update junctionToCircuit to point to new circuit
                    for (var entry : junctionToCircuit.entrySet()) {
                        if (entry.getValue() == closestCircuit) {
                            junctionToCircuit.put(entry.getKey(), junctionBoxCircuit);
                        }
                    }

                    circuits.remove(closestCircuit);
                }
            } else if (!junctionToCircuit.containsKey(junctionBox) && !junctionToCircuit.containsKey(closest)) {
                Set<Triplet<Integer, Integer, Integer>> circuit = new HashSet<>();
                circuit.add(junctionBox);
                circuit.add(closest);
                junctionToCircuit.put(junctionBox, circuit);
                junctionToCircuit.put(closest, circuit);
                circuits.add(circuit);
            } else {
                Triplet<Integer, Integer, Integer> circuited;
                Triplet<Integer, Integer, Integer> nonCircuited;

                if (junctionToCircuit.containsKey(junctionBox)) {
                    circuited = junctionBox;
                    nonCircuited = closest;
                } else {
                    circuited = closest;
                    nonCircuited = junctionBox;
                }

                junctionToCircuit.put(nonCircuited, junctionToCircuit.get(circuited));
                junctionToCircuit.get(circuited).add(nonCircuited);
            }

            connected++;

            if (connected == connectionCount) break;
        }

        List<Integer> circuitSizes = new ArrayList<>();

        for (var circuit : circuits) {
            circuitSizes.add(circuit.size());
        }

        circuitSizes.sort(Integer::compare);

        int lastIndex = circuitSizes.size() - 1;

        return (long) circuitSizes.get(lastIndex) * circuitSizes.get(lastIndex - 1) * circuitSizes.get(lastIndex - 2);
    }

    public double distance(Triplet<Integer, Integer, Integer> box1, Triplet<Integer, Integer, Integer> box2) {
        return Math.sqrt(Math.pow(box1.value0() - box2.value0(), 2) + Math.pow(box1.value1() - box2.value1(), 2) + Math.pow(box1.value2() - box2.value2(), 2));
    }

    public long part2(List<String> lines) {
        List<Set<Triplet<Integer, Integer, Integer>>> circuits = new ArrayList<>();
        Map<Triplet<Integer, Integer, Integer>, Set<Triplet<Integer, Integer, Integer>>> junctionToCircuit = new HashMap<>();

        Set<Triplet<Integer, Integer, Integer>> coordinates = new HashSet<>();

        Set<Triplet<Integer, Integer, Integer>> unconnected = new HashSet<>();


        for (String line : lines) {
            String[] split = line.split(",");

            coordinates.add(new Triplet<>(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }

        unconnected.addAll(coordinates);

        // Create a map of distances to pairs of junction boxes
        TreeMap<Double, Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>> tm = new TreeMap<>();

        for (var junctionBox : coordinates) {
            for (var compareJunctionBox : coordinates) {
                if (junctionBox == compareJunctionBox) continue;

                double d = distance(junctionBox, compareJunctionBox);

                if (tm.containsKey(d)) {
                } else {
                    tm.put(d, new Pair<>(junctionBox, compareJunctionBox));
                }
            }
        }

        Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>> lastPairConnected = null;

        for (var pair : tm.values()) {
            var junctionBox = pair.value0();
            var closest = pair.value1();

            lastPairConnected = pair;

            if (junctionToCircuit.containsKey(junctionBox) && junctionToCircuit.containsKey(closest)) {
                var junctionBoxCircuit = junctionToCircuit.get(junctionBox);
                var closestCircuit = junctionToCircuit.get(closest);

                // Both junction boxes are already on the same circuit - nothing to do
                if (junctionBoxCircuit != closestCircuit) {
                    // Merge closestCircuit into junctionBoxCircuit
                    junctionBoxCircuit.addAll(closestCircuit);

                    // Now update junctionToCircuit to point to new circuit
                    for (var entry : junctionToCircuit.entrySet()) {
                        if (entry.getValue() == closestCircuit) {
                            junctionToCircuit.put(entry.getKey(), junctionBoxCircuit);
                        }
                    }

                    circuits.remove(closestCircuit);
                }
            } else if (!junctionToCircuit.containsKey(junctionBox) && !junctionToCircuit.containsKey(closest)) {
                Set<Triplet<Integer, Integer, Integer>> circuit = new HashSet<>();
                circuit.add(junctionBox);
                circuit.add(closest);

                unconnected.remove(junctionBox);
                unconnected.remove(closest);

                junctionToCircuit.put(junctionBox, circuit);
                junctionToCircuit.put(closest, circuit);
                circuits.add(circuit);
            } else {
                Triplet<Integer, Integer, Integer> circuited;
                Triplet<Integer, Integer, Integer> nonCircuited;

                if (junctionToCircuit.containsKey(junctionBox)) {
                    circuited = junctionBox;
                    nonCircuited = closest;
                } else {
                    circuited = closest;
                    nonCircuited = junctionBox;
                }

                junctionToCircuit.put(nonCircuited, junctionToCircuit.get(circuited));
                junctionToCircuit.get(circuited).add(nonCircuited);

                unconnected.remove(nonCircuited);
            }

            if (unconnected.isEmpty()) break;
        }

        if (lastPairConnected == null) return -1;

        return (long) lastPairConnected.value0().value0() * lastPairConnected.value1().value0();
    }
}