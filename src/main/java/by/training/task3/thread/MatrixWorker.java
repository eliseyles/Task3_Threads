package by.training.task3.thread;

import by.training.task3.entity.Matrix;
import by.training.task3.exception.MatrixIndexOutBoundException;
import by.training.task3.runner.Main;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MatrixWorker extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MatrixWorker.class);

    private int id;
    private CyclicBarrier barrier;
    private Matrix matrix;

    public MatrixWorker(int id, CyclicBarrier cyclicBarrier) {
        super(Integer.toString(id));
        this.id = id;
        this.barrier = cyclicBarrier;
        this.matrix = Matrix.getInstance();
    }

    public void run() {
        try {
            int diagonalIndex = setDiagonalElementValue();
            setElementValue(diagonalIndex);
            barrier.await();
        } catch (MatrixIndexOutBoundException e) {
            LOGGER.warn(e);
        } catch (BrokenBarrierException e) {
            LOGGER.warn(e);
        } catch (InterruptedException e) {
            LOGGER.warn(e);
        }
    }

    private int setDiagonalElementValue() throws MatrixIndexOutBoundException {
        int diagonalIndex = getRandomIndex(matrix.getSize());
        while (!matrix.setValue(diagonalIndex, diagonalIndex, id)) {
            diagonalIndex = getRandomIndex(matrix.getSize());
        }
        return diagonalIndex;
    }

    private int getRandomIndex(int bound) {
        return new Random().nextInt(bound);
    }

    private boolean isRowChosen() {
        return new Random().nextBoolean();
    }

    private void setElementValue(int diagonalIndex) throws IndexOutOfBoundsException {
        int index = getRandomIndex(matrix.getSize());
        try {
            if (isRowChosen()) {
                while (!matrix.setValue(diagonalIndex, index, id)) {
                    index = getRandomIndex(matrix.getSize());
                }
            } else {
                while (!matrix.setValue(index, diagonalIndex, id)) {
                    index = getRandomIndex(matrix.getSize());
                }
            }
        } catch (MatrixIndexOutBoundException e) {
            LOGGER.warn(e);
        }
    }
}
