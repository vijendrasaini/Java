package RaceCondition;
class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter : " + Counter.counter);
    }
}