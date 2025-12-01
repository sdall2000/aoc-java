package common;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static List<String> generateCombinations(List<List<String>> collections) {
        List<String> results = new ArrayList<>();
        if (collections == null || collections.isEmpty()) {
            return results; // Return empty list if input is empty
        }
        generateCombinationsRecursive(collections, 0, "", results);
        return results;
    }

    private static void generateCombinationsRecursive(List<List<String>> collections, int index, String current, List<String> results) {
        // Base case: if we've processed all collections, add the current combination
        if (index == collections.size()) {
            results.add(current);
            return;
        }

        // Recursive case: iterate through the current collection and append its items
        for (String item : collections.get(index)) {
            generateCombinationsRecursive(collections, index + 1, current + item, results);
        }
    }

    public static List<String> generateCombinationsNonRecursive(List<List<String>> collections) {
        List<String> results = new ArrayList<>();
        if (collections == null || collections.isEmpty()) {
            return results; // Return empty list if input is empty
        }

        // Start with an empty combination
        results.add("");

        // Iterate through each collection
        for (List<String> collection : collections) {
            List<String> newResults = new ArrayList<>();
            // Expand each existing combination with each string in the current collection
            for (String combination : results) {
                for (String item : collection) {
                    newResults.add(combination + item);
                }
            }
            // Update results to the new combinations
            results = newResults;
        }

        return results;
    }

    // Courtesy SO
    public static String repeat(char c, int count) {
        return new String(new char[count]).replace('\0', c);
    }
}
