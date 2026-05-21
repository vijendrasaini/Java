package LockFreeConcurrency;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    static AtomicReference<Integer> likes = new AtomicReference<>(0);

    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++) {
            Thread th = new Thread(() -> {
                for(int j = 0; j < 100; j++) {
                    doLike();
                }
            }, "TH-" + i);

            th.start();
        }

        int sleepTime = 5000;
        System.out.println(Thread.currentThread().getName() + " is sleeping for " + sleepTime + " secs");
        System.out.println("Total likes : " + likes.get());
    }

    static void doLike() {
        // retry loop
        int i = 0;
        while(true) {
            int expectedValue = likes.get();
            int newValue = expectedValue + 1;

            if(likes.compareAndSet(expectedValue, newValue)) {
                break;
            }

            // Keep Continue; As CAS failed
            System.out.println("CAS Failed when Thread " + Thread.currentThread().getName() + " was liking in " + i + "-th iteration");
        }
    }
}
