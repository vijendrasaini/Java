package ExecutorFramwork;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("has been picked by : " + Thread.currentThread().getName());
            return 10;
        }).thenApply(result -> {
            System.out.println("'1st thenApply' has been picked by : " + Thread.currentThread().getName());
            return 10 * 20;
        }).thenApply(result -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Exception caught : " + e.getMessage());
            }
            System.out.println("'2st thenApply' has been picked by : " + Thread.currentThread().getName());
            return result + 1;
        });

        
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("has been picked by : " + Thread.currentThread().getName());
            return 10;
        }).thenApply(result -> {
            System.out.println("'1st thenApply' has been picked by : " + Thread.currentThread().getName());
            return 10 * 20;
        }).thenApply(result -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Exception caught : " + e.getMessage());
            }
            System.out.println("'2st thenApply' has been picked by : " + Thread.currentThread().getName());
            return result + 1;
        }).thenAccept(result -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Exception caught : " + e.getMessage());
            }
            System.out.println("'3rd thenAccept' has been picked by : " + Thread.currentThread().getName());
            System.out.println("Result consumed : " + result);
        });

        future2.join(); // Main thread is awaiting for Future to get completed
        System.out.println("Main close");

    }
}
