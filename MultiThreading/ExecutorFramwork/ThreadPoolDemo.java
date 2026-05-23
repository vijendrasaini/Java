package ExecutorFramwork;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    
    // A custom task class so the handler can read the task ID
    static class MyTask implements Runnable {
        private final int taskId;

        public MyTask(int taskId) {
            this.taskId = taskId;
        }

        public int getTaskId() {
            return taskId;
        }

        @Override
        public void run() {
            System.out.println("STARTED BY : " + Thread.currentThread().getName() + " (Task " + taskId + ")");
            try {
                Thread.sleep(3000); // Simulate 3 seconds of work
            } catch (InterruptedException e) {
                System.out.println("Task " + taskId + " was interrupted");
            }
            System.out.println("Finished by : " + Thread.currentThread().getName() + " (Task " + taskId + ")");
        }
    }

    public static void main(String[] args) {
        // Define the thread pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                           // Core Pool Size
                4,                           // Maximum Pool Size
                60, TimeUnit.SECONDS,        // Keep-alive time
                new ArrayBlockingQueue<>(2)  // Queue Capacity
        );

        // Define and set the custom Rejected Execution Handler
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // Cast the runnable back to our custom task to get its ID
                if (r instanceof MyTask) {
                    MyTask task = (MyTask) r;
                    System.err.println("!!! REJECTED !!! Task " + task.getTaskId() + " could not be executed.");
                }
            }
        });

        // Submit 10 tasks
        for (int i = 1; i <= 10; i++) {
            System.out.println("Submitting task : " + i);
            executor.execute(new MyTask(i));

            // Small delay to let the thread pool spin up and show accurate real-time stats
            try { 
                Thread.sleep(20); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("-----------------> Pool Size: " + executor.getPoolSize() 
                    + ", Current Running Threads: " + executor.getActiveCount() 
                    + ", Queue Size: " + executor.getQueue().size());
        }

        // Properly shut down the executor
        System.out.println("Executor is now shutting down...");
        executor.shutdown();
        
        try {
            // Wait for the non-rejected tasks (1, 2, 3, 4, 5, 6) to finish processing
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        System.out.println("All allowed tasks finished. Main thread ending.");
    }
}
