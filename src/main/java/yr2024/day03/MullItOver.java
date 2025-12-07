package yr2024.day03;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {
    public long part1(List<String> lines) {
        var result = 0L;

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Pattern commaPattern = Pattern.compile("(\\d+),(\\d+)");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            
            while (matcher.find()) {
                Matcher commaMatcher = commaPattern.matcher(matcher.group());

                if (commaMatcher.find())
                {
                    result += (Long.parseLong(commaMatcher.group(1)) * Long.parseLong(commaMatcher.group(2)));
                }
            }
        }

        return result;
    }

    public long part2(List<String> lines) {
        var result = 0L;

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        Pattern commaPattern = Pattern.compile("(\\d+),(\\d+)");

        boolean enabled = true;

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            
            while (matcher.find()) {
                String expression = matcher.group();

                if ("do()".equals(expression)) {
                    enabled = true;
                } else if ("don't()".equals(expression)) {
                    enabled = false;
                } else if (enabled) {

                    Matcher commaMatcher = commaPattern.matcher(matcher.group());

                    if (commaMatcher.find())
                    {
                        result += (Long.parseLong(commaMatcher.group(1)) * Long.parseLong(commaMatcher.group(2)));
                    }
                }
 
            }
        }

        return result;
    }
}