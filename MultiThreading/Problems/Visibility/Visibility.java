package Visibility;

public class Visibility {
    public static void main(String[] args) {
        Thread t1 = new Thread1("Thread 1");
        Thread t2 = new Thread2("Thread 2");


        t1.start();
        t2.start();
    }
}
