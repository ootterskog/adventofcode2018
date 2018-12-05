package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static List<String> matchGroups(String string, String regex) {
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile(regex)
                .matcher(string);
        while (m.find()) {
            for (int j = 0; j <= m.groupCount(); j++) {
                allMatches.add(m.group(j));
            }
        }
        return allMatches;
    }
}
