package day1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1Part2 {

    public static void main(String... args) throws Exception {
        Path path = Paths.get("src/main/resources/day1/input.txt");
        List<Long> collect = Files.newBufferedReader(path).lines()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        long currentFrequency = 0;
        ArrayList<Long> pastFrequencies = new ArrayList<>();
        while (true) {
            for(Long delta : collect) {
                if(pastFrequencies.contains(currentFrequency += delta)) {
                    System.out.println(currentFrequency);
                    System.exit(0);
                }
                pastFrequencies.add(currentFrequency);
            }
        }
    }

}
