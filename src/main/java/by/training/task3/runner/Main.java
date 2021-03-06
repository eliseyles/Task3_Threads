package by.training.task3.runner;

import by.training.task3.entity.Matrix;
import by.training.task3.thread.MatrixWorker;
import by.training.task3.thread.WriterThread;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final int Y = 6;
    private static final int N = 5;
    private static final int SEMAPHORE_PERMIT_FOR_ONE_ITERATION = 1;
    private static final String fileName = "output.txt";

    public static void main(String[] args) throws InterruptedException {
        Semaphore iterationSemaphore = new Semaphore(SEMAPHORE_PERMIT_FOR_ONE_ITERATION);
        CyclicBarrier barrierBeforeWriting = new CyclicBarrier(N, new WriterThread(iterationSemaphore, fileName));
        Thread currentThread;
        Matrix.getInstance().initMatrix(N);
        try {
            for (int i = 0; i < Y; i++) {
                iterationSemaphore.acquire();
                for (int j = 0; j < N; j++) {
                    currentThread = new MatrixWorker(i * N + j + 1, barrierBeforeWriting);
                    currentThread.start();
                }
            }
        } catch (InterruptedException e) {
            LOGGER.warn(e);
        }
    }
}
