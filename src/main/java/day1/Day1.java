package day1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day1 {

    public static void main(String... args) throws Exception {
        Path path = Paths.get("src/main/resources/day1/input.txt");
        System.out.println(Files.newBufferedReader(path).lines()
                .mapToLong(Long::parseLong)
                .sum());
    }

}
