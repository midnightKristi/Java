package parallelMatrixMultiply;

import java.util.ArrayList;
import java.lang.Thread;

public class createParallelThreads {

    // creating 10 threads and waiting for them to complete then again repeat steps.
    public static void multiply(int[][] matrix1, int[][] matrix2, int[][] result) {
        ArrayList<Thread> threads = new ArrayList<>();
        int rows1 = matrix1.length;
        for (int i = 0; i < rows1; i++) {
            RowMultiplier task = new RowMultiplier(result, matrix1, matrix2, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            if (threads.size() % 10 == 0) {
                waitForThreads(threads);
            }
        }
    } //end multiply

    private static void waitForThreads(ArrayList<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    } // end waitForThreads

} //end create parallel threads
