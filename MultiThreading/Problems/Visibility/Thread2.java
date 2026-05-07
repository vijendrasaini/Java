package Visibility;

public class Thread2 extends Thread {
    public Thread2(String name) {
        super(name);
    }
    
    public void run() {
        while(!CommonResource.isStopped) {
            // System.out.println("Thread 2 is running");
        }

        System.out.println("Thread 2 is stopped");
        System.out.println("Value of isStopped : " + CommonResource.isStopped);
    }
}
