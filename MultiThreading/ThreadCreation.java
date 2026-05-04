public class ThreadCreation {
    public static void main(String[] args) {
        // using Thread Class
        MyThread t1 = new MyThread();
        t1.start();

        // using Runnable interface
        Thread t2 = new Thread(new MyThread2());
        t2.start();

        // using Lambda
        Thread lt = new Thread(() -> {
            // System.out.println("Thread created using labmda is running");
            // System.out.println("Lambda thread Name : " + Thread.currentThread().getName());
            System.out.println("Inside Lambda : " + Thread.currentThread().getName());
            
        });

        lt.start();
    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        // System.out.println("Thread is running");
        // System.out.println("Inside MyThread : Assigned Thread Name : " + Thread.currentThread().getName());
        System.out.println("Inside MyThread : " + Thread.currentThread().getState());
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        // System.out.println("MyThread 2 is running");
        // System.out.println("Inside MyThread2 : Assigned Thread Name : " + Thread.currentThread().getName());
        System.out.println("Inside MyThread2 : " + Thread.currentThread().getState());
    }
}