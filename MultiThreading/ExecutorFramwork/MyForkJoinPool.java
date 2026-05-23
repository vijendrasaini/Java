package ExecutorFramwork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MyForkJoinPool {
    public static void main(String[] args) {
        ForkJoinPool myPool = new ForkJoinPool();
        int[] myArray = {1, 2, 3, 4, 5, 6};
        MyRecurrsiveTask task = new MyRecurrsiveTask(myArray, 0, myArray.length - 1);
        
        int result = myPool.invoke(task);
        myPool.shutdown();
        System.out.println("Total Sum: " + result); // Output: 21
    }
}

class MyRecurrsiveTask extends RecursiveTask<Integer> {
    private final int[] arr;
    private final int start, end;
    
    // Threshold for stopping recursion
    private static final int THRESHOLD = 2; 

    public MyRecurrsiveTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // FIX 1: Check the current range size, not the full array length
        if ((end - start + 1) <= THRESHOLD) {
            int sum = 0;
            // FIX 3: Loop only through the assigned chunk of the array
            for (int i = start; i <= end; i++) {
                sum += arr[i];
            }
            return sum;
        }

        int mid = (start + end) / 2;
        MyRecurrsiveTask leftTask = new MyRecurrsiveTask(arr, start, mid);
        MyRecurrsiveTask righTask = new MyRecurrsiveTask(arr, mid + 1, end);

        // FIX 2: Correct order of fork/join to run tasks asynchronously
        righTask.fork();                      // Push right task to queue for another thread
        int leftResult = leftTask.compute();   // Current thread computes the left task
        int rightResult = righTask.join();     // Wait for and read the right task result

        return leftResult + rightResult;
    }
}
