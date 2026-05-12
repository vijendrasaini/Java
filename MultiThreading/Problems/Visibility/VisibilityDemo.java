package Visibility;

public class VisibilityDemo {

    static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            System.out.println("Worker started");

            while (running) {
                // busy wait
            }

            System.out.println("Worker stopped");
        });

        worker.start();

        // give worker time to start
        Thread.sleep(1000);

        System.out.println("Main thread updating running = false");
        running = false;

        worker.join();
        System.out.println("Main thread finished");
    }
}