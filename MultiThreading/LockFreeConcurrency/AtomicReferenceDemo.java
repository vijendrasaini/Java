package LockFreeConcurrency;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    static AtomicReference<Integer> likes = new AtomicReference<>(0);

    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 100; j++) {
                    doLike();
                }
            }, "TH-" + i);

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("Total likes : " + likes.get());
    }

    static void doLike() {
        // retry loop
        int i = 0;
        while(true) {
            int expectedValue = likes.get();
            int newValue = expectedValue + 1;

            if(likes.compareAndSet(expectedValue, newValue)) {
                if(i > 0) {
                    System.out.println("CAS succeeded for thread : " + Thread.currentThread().getName() + " in " + i + "-th iteration");
                }
                break;
            }

            i++;
            // Keep Continue; As CAS failed
        }
    }
}
