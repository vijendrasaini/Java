package Locks.Custom;

import java.util.concurrent.locks.StampedLock;

public class MyStampedLock {
    public static void main(String[] args) {
        // Define worker tasks using modern lambda expressions
        Runnable writeWorker = () -> SharedResource.writeData();
        Runnable readWorker = () -> SharedResource.readData();
        Runnable optimisticReadWorker = () -> SharedResource.optimisticReadData();

        // Spawning 10 Writer, 10 Pessimistic Reader, and 10 Optimistic Reader threads
        for (int i = 1; i <= 10; i++) {
            Thread wt = new Thread(writeWorker, "WT-" + i);
            wt.start();
            
            Thread rt = new Thread(readWorker, "RT-" + i);
            rt.start();

            Thread ort = new Thread(optimisticReadWorker, "ORT-" + i);
            ort.start();
        }
    }
}

class SharedResource {
    private static volatile int counter = 0;
    private static final StampedLock lock = new StampedLock();

    /**
     * 1. WRITE DATA (Exclusive Mode)
     * Acquires a hard, exclusive lock. Blocks all other readers and writers.
     */
    public static void writeData() {
        Thread currThread = Thread.currentThread();
        System.out.println("WriteAttempt by %s".formatted(currThread.getName()));
        
        long stamp = lock.writeLock(); // Heavy exclusive lock
        try {
            System.out.println("Writing by %s has started ...".formatted(currThread.getName()));
            Thread.sleep(1000); // Simulate heavy writing workload

            counter++;
            System.out.println("Written by %s | New Counter Value: %d".formatted(currThread.getName(), counter));
        } catch (InterruptedException e) {
            System.err.println("%s was interrupted during write sleep.".formatted(currThread.getName()));
            Thread.currentThread().interrupt(); 
        } finally {
            lock.unlockWrite(stamp); // Free lock using the explicit write stamp
        }
    }

    /**
     * 2. PESSIMISTIC READ DATA (Shared Mode)
     * Acquires a traditional shared read lock. Allows multiple readers simultaneously 
     * but blocks incoming writers. Returns the safely read counter value.
     */
    public static int readData() {
        Thread currThread = Thread.currentThread();
        System.out.println("Pessimistic Read Attempt by %s".formatted(currThread.getName()));
        
        long stamp = lock.readLock(); // Shared blocking lock
        try {
            System.out.println("Pessimistic Reading by %s has acquired lock...".formatted(currThread.getName()));
            Thread.sleep(1000); // Simulate reading processing workload
            return counter;
        } catch (InterruptedException e) {
            System.err.println("%s was interrupted during read sleep.".formatted(currThread.getName()));
            Thread.currentThread().interrupt(); 
            return counter; // Return best effort state
        } finally {
            lock.unlockRead(stamp); // Free lock using the explicit read stamp
        }
    }

    /**
     * 3. OPTIMISTIC READ DATA (Lock-free Speculative Mode)
     * Pulls a version stamp passively without acquiring a heavy structural lock.
     * Validates if a collision occurred mid-read, falling back to readData() if necessary.
     */
    public static void optimisticReadData() {
        Thread currThread = Thread.currentThread();
        System.out.println("Optimistic Read Attempt by %s".formatted(currThread.getName()));
        
        // Step A: Capture the speculative version stamp (Non-blocking)
        long stamp = lock.tryOptimisticRead();

        // Step B: Pull a snapshot copy of the shared resource into the thread stack
        int localCounter = counter;

        // Introduce a short window to simulate localized calculations, allowing concurrent writes to occur
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}

        // Step C: Validate if any writer thread modified the stamp during our read step
        if (!lock.validate(stamp)) {
            System.out.println("Validation FAILED for %s! Data was modified mid-read. Redirecting to fallback...".formatted(currThread.getName()));
            
            // Step D: Fallback Path - Call the standard pessimistic readData method to acquire a clean, safe state
            localCounter = readData(); 
        } else {
            System.out.println("Validation PASSED for %s! Highly efficient read operation achieved.".formatted(currThread.getName()));
        }

        // Step E: Safely print or use the validated/recovered data
        System.out.println("Final Result used by %s | Computed Value: %d".formatted(currThread.getName(), localCounter));
    }
}