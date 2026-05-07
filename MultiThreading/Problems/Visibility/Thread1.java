package Visibility;

public class Thread1 extends Thread {
    public Thread1(String name) {
        super(name);
    }

    public void run() {
        int sleepTime = 2;
        System.out.println("Thread is sleeping for " + sleepTime * 1000 + " seconds");
        System.out.println("Value of isStopped : " + CommonResource.isStopped);
        try {
            Thread.sleep(sleepTime * 1000 );
            CommonResource.isStopped = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
