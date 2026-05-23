package Locks.Custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyConditions {
    static final int MAX_CAPACITY = 5;
    static List<Integer> list = new ArrayList<>();
    static ReentrantLock lock = new ReentrantLock();
    static final Condition producerCondition = lock.newCondition();
    static final Condition consumerCondition = lock.newCondition();
    
    public static void main(String[] args) {
        
        for(int i = 1; i <= 10; i++) { 
            Thread producer = new Thread(() -> {
                try {
                    int data = new Random().nextInt(0, 100);
                    produce(data);
                    Thread currentThread = Thread.currentThread();
                    System.out.println(currentThread.getName() + " produced : " + data);
                } catch (Exception e) {
                    System.out.println("Something wrong happened to Write : " + e);
                 }
            }, "Producer" + i);

            Thread consumer = new Thread(() -> {
                try {
                    int data = consume();
                    Thread currentThread = Thread.currentThread();
                    System.out.println(currentThread.getName() + " consumed : " + data);
                } catch (Exception e) {// OR be explicit:
                    System.out.println("Exception Type: " + e.getClass().getSimpleName() + " Exception Message: " + e.getMessage());
                    System.out.println();
                 }
            }, "Consumer" + i);

            producer.start();
            consumer.start();
        }
    }

    public static void produce(int value) throws InterruptedException {
        lock.lock();
        Thread currentThread = Thread.currentThread();
        try {
            while(list.size() == MAX_CAPACITY) {
                System.out.println("List is full. Thread " + currentThread.getName() + " will wait now.");
                producerCondition.await();
            }

            System.out.println("Thread " + currentThread.getName() + " is producing the data ...");
            Thread.sleep(2000);
            int data = value;
            list.add(data);
            System.out.println("Data : "+ data+" has been produced. Notifying to Consumers");
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static int consume() throws InterruptedException {
        lock.lock();
        Thread currentThread = Thread.currentThread();
        try {
            while(list.isEmpty()) {
                System.out.println("List is empty. Thread " + currentThread.getName() + " will wait now.");
                consumerCondition.await();
            }

            System.out.println("Thread " + currentThread.getName() + " is consuming the data ...");
            Thread.sleep(2000);
            int data = list.removeLast();
            System.out.println("Data : "+ data+" has been consumerd. Notifying to Producers");
            producerCondition.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }
}
