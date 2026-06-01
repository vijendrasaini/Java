package Streams.IntermediateMethods;

import java.util.Arrays;
import java.util.List;

public class SortingAndDebugging {
    public static void main(String[] args) {
        //1. sorted ( natural : ascending for numbers)
        // Arrays.stream(new int[]{1, 5, 4, 2, 2, 4, 99, 12, 23}).sorted().forEach(System.out::println);

        // List.of("zebra", "black", "humba", "apple").stream().sorted().forEach(System.out::println);

        // 2. peek ( used to look into a step)
        Arrays.stream(new int[]{1, 2, 3, 4, 4, 5, 0, 1, 2})
            .peek(System.out::println)
            .map(n -> n * 10)
            .peek(System.out::println)
            .filter(n -> n % 2 == 0)
            .peek(System.out::println)
            .skip(2)
            .forEach(System.out::println);
    }
}
