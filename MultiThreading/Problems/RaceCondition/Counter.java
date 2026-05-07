package RaceCondition;

public class Counter {
    public static int counter = 0;

    public static void increment() {
        counter++;
    }

    public static synchronized void threadSafeIncrement() {
        counter++;
    }
}
