package Streams.IntermediateMethods;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MapAndShape {
    public static void main(String[] args) {
        
        Stream<Integer> stream = List.of(1, 2, 3, 4, 5, 6, 6, 6).stream();

        //1. map
        // stream.map(n -> n * 10).forEach(System.out::println);

        // 2. flatMap
        // Stream<List<Integer>> listStream = List.of(List.of(1, 2, 3), List.of(4, 4, 4)).stream();
        // listStream.forEach(System.out::println);
        // listStream.flatMap(innerList -> innerList.stream()).forEach(System.out::println);
        // listStream.flatMap(List::stream).forEach(System.out::println); // short hand

        // 3. mapToInt
        // 4. mapToDouble
        // 5. mapToLong
        // stream.mapToInt(n -> n).forEach(System.out::println); // unnecessary
        // List.of("vijendra", "shimbhu", "deepak").stream().mapToInt(String::length).forEach(System.out::println);

        // 6. mapToObject
        // IntStream intStream = Arrays.stream(new int[]{1, 2, 4, 5, 10});
        // Stream<String> genStrema = intStream.mapToObj( n  -> "Number-" + n);
        // genStrema.forEach(System.out::println);
    }
}
