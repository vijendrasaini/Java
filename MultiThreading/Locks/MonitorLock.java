package Locks;

public class MonitorLock {
    static int count = 1;
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

    public static synchronized void update() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            
        }

        increment();
        System.out.println("Modified by : " + Thread.currentThread().getName() + " count : " + count);
    }

    public static synchronized void increment() {
        count ++;
    }
}

/*
Issues with Monitor lock : 
1. No control on locking and unlocking
2. Fairness ( startvation )
3. Keeps other thread in blocked state. However thread should be able to do something else if it can't acquire lock but no such feature in lock.

Nature : reentrant

*/