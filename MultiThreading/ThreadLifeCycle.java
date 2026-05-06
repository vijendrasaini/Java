public class ThreadLifeCycle {
    public static void main(String[] args) throws InterruptedException{
        Thread mainThread = Thread.currentThread();

        // System.out.println(mainThread.getName()); // main

        Thread myThread = new Thread(() -> {
            // System.out.println("Inside " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        // setting up a custom name
        myThread.setName("MY-Thread");
        // System.out.println(myThread.getName());

        // NEW
        System.out.println(myThread.getState());

        // RUNNABLE
        myThread.start();
        System.out.println(myThread.getState());

        
        // Running ( however Java doesn't differential b/w Runnable & Running state)
        System.out.println(mainThread.getState().name() == "RUNNABLE" ? "RUNNING" : "Not Running");

        // TIMED_WAITING
        Thread.sleep(200);
        System.out.println(myThread.getState());

        // TERMINATED
        myThread.join();
        System.out.println(myThread.getState());

    }
}
