package Ordering;

public class ReorderingExp2 {

    static int a = 0, x = 0;
    static int b = 0, y = 0;
    public static void main(String[] args) throws InterruptedException {
        testReordering();
    }

    public static void testReordering() throws InterruptedException {
        int count = 0;
        while (true) {
            a = b = x = y = 0;
            Thread t1 = new Thread(() -> {
                a = 1; // T1 writes to a
                x = b; // T1 reads from b ( T1 observes changes from Thread T2 )
            });

            Thread t2 = new Thread(() -> {
                b = 1; // T2 writes to b
                y = a; // T2 reads from a ( T2 observes changes from Thread T1 )
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            count++;

            if(x == 0 && y == 0) {
                System.out.println("Reordering happened at iteration: " + count);
                break;
            }

            if(count % 10000 == 0) {
                System.out.println("Iteration: " + count);
            }

            if(count > 100000) {
                System.out.println("No reordering happened after iteration: " + count);
                break;
            }
        }


    }

    public static void testAsIfSerial() throws InterruptedException {
        int count = 0;
        while (true) {
            count++;
            a = x = 0;
            Thread t1 = new Thread(() -> {
                a = 1;
                x = a;
            });

            t1.start();
            t1.join();

            if (x == 0) {
                System.out.println("Reordering happened at iteration: " + count);
                break;
            }


            if(count % 10000 == 0) {
                System.out.println("Iteration: " + count);
            }

            if(count > 100000) {
                System.out.println("No reordering happened after iteration: " + count);
                break;
            }
        }
    
    }
}

// Here Reordering does not happen due As if serial Rule of a single thread

// As if serial Rule of a single thread : 
// What Is As-If-Serial Guarantee?

// The rule says:

// JVM/compiler/CPU may freely optimize and reorder instructions
// AS LONG AS the result appears identical to single-threaded execution.