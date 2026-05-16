package Locks.Custom;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReEntrantLock {
    static int count = 0;
    static ReentrantLock entrantLock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException{

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                update();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                update();
            }
        });


        t1.setName("T1");
        t2.setName("T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Proceess is done");
    }

    public static void update() {
        
        entrantLock.lock();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            
        }
        System.out.println("Lock is acquired");
        try {
            increment();
            System.out.println("Modified by : " + Thread.currentThread().getName() + " count : " + count);
        } finally {
            entrantLock.unlock();
            // System.out.println("Lock is released");
        }
    }

    public static void increment() {
        if(entrantLock.tryLock()) {
            // System.out.println("2nd Lock is acquired");
            
            try {
                count ++;
                System.out.println("additional info : hold count " + entrantLock.getHoldCount() + " , is lock Fair : " + entrantLock.isFair());
            } finally {
                entrantLock.unlock();
                System.out.println("additional info : hold count " + entrantLock.getHoldCount() + " , is lock Fair : " + entrantLock.isFair());
                System.out.println("2nd Lock is released");
            }
        } else {
            System.out.println("Tryied but didn't acquire");
        }
    }
}

/*
Issues with Monitor lock : 
1. No control on locking and unlocking
2. Fairness ( startvation )
3. Keeps other thread in blocked state. However thread should be able to do something else if it can't acquire lock but no such feature in lock.

Nature : reentrant

*/