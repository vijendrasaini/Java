package RaceCondition;

public class Thread1 extends Thread {
    public void run() {
        for(int i = 1; i <= 1000; i++) {
            // Counter.increment();
            Counter.threadSafeIncrement();
        }
    }
}
