package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String[] args) {
        // Ways of creating Streams
        
        // 1. Using Arrays.stream() method -> Stream<T> or IntStream | LongStream | DoubleStream
        int[] arr = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(arr);
        // FIX: Removed 'int' from lambda parameters
        stream = stream.filter(n -> n % 2 == 0).map(n -> n * 10); 


        // 2. Collection interface's stream() method -> Stream<T>
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Stream<Integer> stream2 = list.stream();
        // FIX: Removed 'Integer', and assigned to a new stream variable
        Stream<Integer> processedStream2 = stream2.filter(n -> n % 2 == 0).map(n -> n * 10);

        // 3. Stream.of static method -> Stream<T>
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
        
        // 4. Empty Stream using Stream.empty()
        Stream<String> stream4 = Stream.empty();
        IntStream stream5 = IntStream.empty();

        // 5. Stream.builder().add().build()
        Stream<Integer> stream6 = Stream.<Integer>builder().build();
        Stream<Integer> stream7 = Stream.<Integer>builder()
            .add(1)
            .add(2)
            .add(3)
            .add(4)
            .add(5)
            .build();

        // 6. Infinite Stream
        Stream<Integer> stream8 = Stream.iterate(10, (Integer n) -> n + 1).limit(10);
        Stream<Double> stream9 = Stream.generate(Math::random).limit(10);
        
        System.out.println("All conveyor belts built perfectly!");
    }
}