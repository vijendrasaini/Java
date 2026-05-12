package Ordering;

public class OrderingDemo {

    static int x = 0;
    static int y = 0;
    static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        int iterations = 0;

        while (true) {
            iterations++;

            x = 0;
            y = 0;
            flag = false;

            Thread t1 = new Thread(() -> {
                x = 1;
                y = 2;
                flag = true;
            });

            Thread t2 = new Thread(() -> {
                if (flag) {
                    if (y == 0 || x == 0) {
                        System.out.println("⚠️ Reordering observed!");
                        System.out.println("x=" + x + ", y=" + y);
                        System.exit(0);
                    }
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            if (iterations % 10000 == 0) {
                System.out.println("Iterations: " + iterations);
            }
        }
    }
}