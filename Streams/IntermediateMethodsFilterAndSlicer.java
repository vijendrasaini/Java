package Streams;

import java.util.List;
import java.util.stream.Stream;

public class FilterAndSlicer {
    public static void main(String[] args) {
        Stream<Integer> stream = List.of(1, 2, 3, 4, 5, 6, 6, 6).stream();

        // 1. filter
        // stream.filter(n -> n % 2 == 0).forEach(n -> System.out.println(n));

        // 2. skip
        // stream.skip(2).forEach(System.out::println);

        // 3. limit
        // stream.limit(2).forEach(System.out::println);

        // 4. distinct
        // stream.distinct().forEach(System.out::println);

        // 5. dropWhile
        // stream.dropWhile(n -> n < 5).forEach(System.out::println);

        // 6. takeWhile
        // stream.takeWhile(n -> n % 2 == 0).forEach(System.out::println); // stream will shutdown 
        stream.takeWhile(n -> n < 5).forEach(System.out::println);
    }
}
