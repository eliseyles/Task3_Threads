package by.training.task3.runner;

import by.training.task3.entity.Matrix;
import by.training.task3.thread.MatrixWorker;
import by.training.task3.thread.WriterThread;

import java.io.File;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    private static final String OUTPUT_FILENAME = "output.txt";
    private static final int Y = 2;
    private static final int N = 2;

    private static File outputFile = new File(OUTPUT_FILENAME);

    public static void main(String[] args) {
        Matrix.getInstance().initMatrix(N);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new WriterThread(outputFile));
        Semaphore semaphore = new Semaphore(N, true);
        Thread currentThread;

        try {
            for (int i = 0; i < Y; i++) {
                semaphore.acquire();
                for (int j = 0; j < N; j++) {
                    currentThread = new MatrixWorker(i * N + j, cyclicBarrier);
                    System.out.printf("thread %s started\n", currentThread.getName());
                    currentThread.start();
                    semaphore.release();
                }
            }
        } catch (InterruptedException e) {
//            log
        }

    }
}
