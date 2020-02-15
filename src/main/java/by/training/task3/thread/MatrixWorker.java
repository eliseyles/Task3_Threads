package by.training.task3.thread;

import by.training.task3.entity.Matrix;
import by.training.task3.exception.MatrixIndexOutBoundException;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
//todo add private logger

public class MatrixWorker extends Thread {
    private int id;
    private CyclicBarrier cyclicBarrier;
    private Matrix matrix;

    public MatrixWorker(int id, CyclicBarrier cyclicBarrier) {
        super(Integer.toString(id));
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.matrix = Matrix.getInstance();
    }

    public void run() {

        try {
            int diagonalIndex = setDiagonalElementValue();

            setElementValue(diagonalIndex);

            cyclicBarrier.await();

        } catch (MatrixIndexOutBoundException e) {
//            LOG.error(e);
        } catch (BrokenBarrierException e) {
//            LOG.warn(e);
        } catch (InterruptedException e) {
//            LOG.warn(e);
            Thread.currentThread().interrupt();
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
        while (index == diagonalIndex) {
            index = getRandomIndex(matrix.getSize());
        }
        try{
        if (isRowChosen()) {
            while (!matrix.setValue(diagonalIndex, index, diagonalIndex)){
                index = getRandomIndex(matrix.getSize());
            }
        } else {
            while (!matrix.setValue(index, diagonalIndex, diagonalIndex)){
                index = getRandomIndex(matrix.getSize());
            }
        }
        }catch (MatrixIndexOutBoundException e) {
//            log
        }
    }
}
