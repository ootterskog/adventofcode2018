package day3;

import util.Regex;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static util.Filereading.getLinesForDay;

public class Day3Part1 {

    public static void main(String... args) throws Exception {
        List<String> lines = getLinesForDay(3);
        Map<String, List<Point>> claims = lines.stream()
                .collect(Collectors.toMap(claim -> claim.split(" ")[0], Day3Part1::getPoints));
        System.out.println(claims.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .filter(cnt -> cnt > 1)
                .count());
    }

    private static List<Point> getPoints(String claim) {
        List<Integer> numbersInClaim = Regex.matchGroups(claim, "(\\d+)").stream().map(Integer::parseInt).collect(Collectors.toList());
        return IntStream.range(
                numbersInClaim.get(1),
                numbersInClaim.get(1) + numbersInClaim.get(3)
        ).mapToObj(x ->
                IntStream.range(
                        numbersInClaim.get(2),
                        numbersInClaim.get(2) + numbersInClaim.get(4)
                )
                        .mapToObj(y -> new Point(x, y))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }


}
