package day2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2Part1 {

    public static void main(String... args) throws Exception {
        Path path = Paths.get("src/main/resources/day2/input.txt");
        Map<Integer, Long> collect = Files.newBufferedReader(path).lines()
                .flatMap(line -> Stream.of(line.split(""))
                        .collect(Collectors.groupingBy(Function.identity()))
                        .entrySet().stream()
                        .map(map -> map.getValue().size())
                        .distinct()
                ).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect.get(2) * collect.get(3));
    }

}


