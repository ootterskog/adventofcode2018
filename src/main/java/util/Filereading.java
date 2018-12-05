package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Filereading {

    public static List<String> getLinesForDay(int day) {
        try {
            return Files.newBufferedReader(Paths.get("src/main/resources/day" + day + "/input.txt"))
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
