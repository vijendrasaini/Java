public class SynchronizedKeyword {
    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();
        Thread t1 = new MyThread(counter);
        Thread t2 = new MyThread(counter);

        t1.setName("T1");
        t2.setName("T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCounter());
    }
}

class MyThread extends Thread {
    private Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 1000; i++) {
            counter.increment();
        }
    }
}


class Counter {
    private int counter;
    public Counter() {
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public synchronized void increment() {
        counter++;
    }
}