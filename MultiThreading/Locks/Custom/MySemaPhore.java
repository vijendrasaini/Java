package Locks.Custom;

import java.util.concurrent.Semaphore;

public class MySemaPhore {
    private static Semaphore lock = new Semaphore(3);
    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++) { 
            Thread thread = new Thread(() -> {
                try {
                    limitedAccessMethod();
                } catch (Exception e) { }
            }, "Th" + i);

            thread.start();
        }

        System.out.println("Processed is finished");
    }

    private static void limitedAccessMethod() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + " Trying to acqure the permits. Current Permits : " + lock.availablePermits());
        lock.acquire();
        System.out.println(" 1 Permit acquired by " + currentThread.getName() + ". Remaining Permits : " + lock.availablePermits());
        try {
            Thread.sleep(2000);
        } finally {
            System.out.println("1 Permit is released by " + currentThread.getName() + ". Remaining Permits : " + lock.availablePermits());
            lock.release();
        }

    }
}
