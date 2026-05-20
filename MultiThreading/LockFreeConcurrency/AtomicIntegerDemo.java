package LockFreeConcurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    static AtomicInteger counter = new AtomicInteger(0);
    static int intCounter = 0;
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
                for(int i = 0; i < 10000; i++) {
                    increment();
                }
            }, "T1");
            Thread t2 = new Thread(() -> {
                for(int i = 0; i < 10000; i++) {
                    increment();
                }
            }, "T2");

            t1.start();
            t2.start();

            t1.join();
            t2.join();

        System.out.println("Counter : " + counter.get());
        System.out.println("intCounter : " + intCounter);
    }

    public static void increment() {
        intCounter++; // Not atomic
        counter.incrementAndGet(); // Atomic
    }
}
