package day2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day2Part2 {

    public static void main(String... args) throws Exception {
        Path path = Paths.get("src/main/resources/day2/input.txt");
        List<String> collect = Files.newBufferedReader(path).lines().collect(Collectors.toList());
        System.out.println(collect.stream()
                .map(line -> findCommon(line, collect))
                .filter(Optional::isPresent)
                .findFirst().get());
    }

    private static Optional<String> findCommon(String line, List<String> collect) {
        return collect.stream()
                .map(candidate -> mapToEqualChars(candidate, line))
                .filter(newLine -> newLine.length() == line.length() - 1)
                .findFirst();
    }

    private static String mapToEqualChars(String candidate, String line) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < candidate.length(); i++) {
            if(candidate.substring(i, i+1).equals(line.substring(i, i+1))) {
                sb.append(candidate.substring(i, i+1));
            }
        }
        return sb.toString();
    }

}


