public class ThreadStates {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread Name : " + Thread.currentThread().getName());
            System.out.println("current Status : " + Thread.currentThread().getState());
        });

        System.out.println("State :" + t1.getState());

        
        try{
            t1.start();
    
            System.out.println("Thread Status : " + t1.getState());
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // System.out.println("Thread Status : " + t1.getState());
        // System.out.println("Thread Status : " + t1.getState());
        // System.out.println("Thread Status : " + t1.getState());
        // System.out.println("Thread Status : " + t1.getState());
    }
}
