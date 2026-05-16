package Locks.Custom;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

public class MyReadWriteLock {
    // Volatile ensures changes made by one thread are immediately visible to others
    static volatile int counter = 0;
    
    // Initializing the ReadWriteLock hierarchy (Fairness parameter set to true optional)
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        // Fix 1: Fixed the variable name typo from 'readWroked' to 'readWorker'
        Runnable writeWorker = () -> writeData();
        Runnable readWorker = () -> readData();

        // Spawning 10 Writer and 10 Reader threads to simulate concurrency
        for (int i = 1; i <= 10; i++) {
            Thread wt = new Thread(writeWorker, "WT-" + i);
            wt.start();
            
            Thread rt = new Thread(readWorker, "RT-" + i);
            rt.start();
        }
    }

    public static void writeData() {
        Thread currThread = Thread.currentThread();
        // Fix 2: Log the attempt BEFORE acquiring the lock to monitor thread contention
        System.out.println("WriteAttempt by %s".formatted(currThread.getName()));
        
        writeLock.lock();
        try {
            System.out.println("Writing by %s has started ...".formatted(currThread.getName()));
            Thread.sleep(1000);

            counter++;
            System.out.println("Written by %s | New Counter Value: %d".formatted(currThread.getName(), counter));
        } catch (InterruptedException e) {
            // Fix 3: Handle the InterruptedException properly instead of swallowing it
            System.err.println("%s was interrupted during write sleep.".formatted(currThread.getName()));
            Thread.currentThread().interrupt(); 
        } finally {
            writeLock.unlock();
        }
    }

    public static void readData() {
        Thread currThread = Thread.currentThread();
        // Fix 2: Log the attempt BEFORE acquiring the lock to see real concurrent accumulation
        System.out.println("ReadAttempt by %s".formatted(currThread.getName()));
        
        readLock.lock();
        try {
            System.out.println("Reading by %s has acquired lock... (Simultaneous with other readers)".formatted(currThread.getName()));
            Thread.sleep(1000);
            System.out.println("Reading completed by %s | Shared Counter Value: %d".formatted(currThread.getName(), counter));
        } catch (InterruptedException e) {
            // Fix 3: Handle the InterruptedException properly instead of swallowing it
            System.err.println("%s was interrupted during read sleep.".formatted(currThread.getName()));
            Thread.currentThread().interrupt(); 
        } finally {
            readLock.unlock();
        }
    }
}