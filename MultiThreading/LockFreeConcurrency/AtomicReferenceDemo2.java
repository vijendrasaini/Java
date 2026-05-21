package LockFreeConcurrency;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo2 {

    static AtomicReference<Integer> likes = new AtomicReference<>(0);

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {

            threads[i] = new Thread(() -> {

                for (int j = 0; j < 100; j++) {
                    doLike();
                }

            }, "TH-" + (i + 1));

            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nFinal Total Likes : " + likes.get());
    }

    static void doLike() {

        int retries = 0;

        // CAS Retry Loop
        while (true) {

            Integer expectedValue = likes.get();

            Integer newValue = expectedValue + 1;

            // CAS Operation
            if (likes.compareAndSet(expectedValue, newValue)) {

                // Optional logging for understanding retries
                if (retries > 0) {
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " succeeded after retries : "
                                    + retries
                    );
                }

                return;
            }

            // CAS Failed → Retry
            retries++;
        }
    }
}