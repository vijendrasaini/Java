package Streams;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class TerminateStream {
    public static void main(String[] args) {
        // 1. forEach & forEachOrdered
        // IntStream intStream = Arrays.stream(new int[]{1, 2, 23, 34, 3, 1, 0, 3}).sorted();
        // Stream<Integer> parallelStream = List.of(1, 2, 3, 4, 5, 6, 7, 8).parallelStream();

        // parallelStream.forEach(System.out::println); // Unordered elemetns
        // parallelStream.forEachOrdered(System.out::println); // ordered elements

        // 2. Aggregators

        // a. toList
        Stream<Integer> agStream = List.of(1, 2, 3, 4, 5, 6, 7).stream();
        // List<Integer> list = agStream.toList();
        // System.out.println(list);

        // b. toArray
        // Object[] resultArray = agStream.toArray();
        // Object[] resultArray2 = agStream.toArray();
        // System.out.println(Arrays.toString(resultArray));
        // System.out.println(Arrays.toString(resultArray2));

        // c. iterator
        // Iterator<Integer> iterator = agStream.iterator();
        // while(iterator.hasNext()) {
        //     System.out.println(iterator.next());
        // }

        // d. splitIterator
        // Spliterator<Integer> spliterator = agStream.spliterator();
        // spliterator.forEachRemaining(System.out::println);
        // spliterator.tryAdvance(System.out::println);
        // spliterator.tryAdvance(System.out::println);
        // spliterator.tryAdvance(System.out::println);
        // spliterator.tryAdvance(System.out::println);
        // Spliterator<Integer> firstHalf = spliterator.trySplit();
        // firstHalf.forEachRemaining(System.out::println);
        // spliterator.forEachRemaining(System.out::println);

        // e. collect
        // List<Integer> list = agStream.filter(n -> n % 2 == 0).collect(Collectors.toList());
        // System.out.println(list);

        //f. count()
        // System.out.println(agStream.filter(n -> n > 6).count());

        //e. reduce
        // Optional<Integer> optInteger = agStream.reduce((n1, n2) -> n1 + n2);
        // if(optInteger.isEmpty()) {
        //     System.out.println("No data");
        // } else {
        //     System.out.println(optInteger.get());
        // }

        // f. findFirst
        // System.out.println(agStream.findFirst());

        // g. findAny
        // System.out.println(List.of(1, 2, 3, 4, 5, 6, 7, 8).parallelStream().findAny());

        // h. anyMatch
        // System.out.println(agStream.anyMatch(n -> n == 4)); // true
        // System.out.println(agStream.anyMatch(n -> n == 9)); // false

        // i. allMatch
        // System.out.println(agStream.allMatch(n -> n < 10)); // true
        // System.out.println(agStream.allMatch(n -> n < 7)); // false

        // j. noneMatch
        System.out.println(agStream.noneMatch(n -> n > 10)); // true
    }
}
