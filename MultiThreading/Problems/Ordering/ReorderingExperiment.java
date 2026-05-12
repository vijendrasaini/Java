package Ordering;
public class ReorderingExperiment {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        int count = 0;

        while (true) {

            count++;

            x = y = a = b = 0;

            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            if (x == 0 && y == 0) {
                System.out.println("Reordering happened at iteration: " + count);
                break;
            }
        }
    }
}