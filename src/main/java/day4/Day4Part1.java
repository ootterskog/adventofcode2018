package day4;

import util.Regex;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static util.Filereading.getLinesForDay;

public class Day4Part1 {
/*
[1518-08-11 00:56] wakes up
[1518-10-10 23:52] Guard #2707 begins shift
 */
    public static void main(String... args) throws Exception {
        List<String> lines = getLinesForDay(4);

        Stack<Long> times = new Stack<>();
        String currentCuard = null;
        Map<String, List<Long>> guardSleepingMinutes = new HashMap<>();
        List<AbstractMap.SimpleEntry<LocalDateTime, String>> collect = lines.stream().map(line -> new AbstractMap.SimpleEntry<>(
                LocalDateTime.parse(
                        Regex.matchGroups(line, "\\[(.*)\\]").get(1),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), Regex.matchGroups(line, "\\[(.*)\\] (.*)").get(2)))
                .sorted(Comparator.comparing(AbstractMap.SimpleEntry::getKey))
                .collect(Collectors.toList());
        for(Map.Entry<LocalDateTime, String> entry : collect) {
            if(entry.getValue().startsWith("Guard")) {
                currentCuard = Regex.matchGroups(entry.getValue(), ".*#(\\d+)").get(1);
            } else {
                if(times.isEmpty()) {
                    times.push(Long.parseLong(Regex.matchGroups(entry.getKey().toString(), ".*\\:(\\d+)").get(1)));
                } else {
                    guardSleepingMinutes.merge(currentCuard,
                        LongStream.range(times.pop(), Long.parseLong(Regex.matchGroups(entry.getKey().toString(), ".*:(\\d+)").get(1)))
                                .boxed()
                                .collect(Collectors.toList()),
                            (list1, list2) ->
                                    Stream.of(list1, list2)
                                            .flatMap(Collection::stream)
                                            .collect(Collectors.toList()));
                }
            }
        }
        Long mostSleptMinute = guardSleepingMinutes.entrySet().stream()
                .sorted((f1, f2) -> Long.compare(f2.getValue().size(), f1.getValue().size()))
                .findFirst()
                .map(guard -> {
                    System.out.println(guard.getKey());
                    return guard.getValue().stream()
                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                })
                .get().entrySet().stream()
                .sorted((f1, f2) -> Long.compare(f2.getValue(), f1.getValue()))
                .findFirst().get().getKey();
        System.out.println(mostSleptMinute);
    }


}
