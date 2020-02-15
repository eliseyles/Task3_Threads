package by.training.task3.thread;

import by.training.task3.entity.Matrix;

import java.text.Format;
import java.util.Formatter;
import java.util.concurrent.Callable;

public class MatrixSummator implements Callable {
    static final String TEMPLATE = "Thread %d sum %d\n";

    @Override
    public String[] call() throws Exception {
        Matrix matrix = Matrix.getInstance();
        String[] result = new String[matrix.getSize()];
        int threadSum;
        for (int i = 0; i < matrix.getSize(); i++) {
            threadSum = 0;
            for (int j = 0; j < matrix.getSize(); j++) {
                threadSum += matrix.getElementValue(i, j);
                threadSum += matrix.getElementValue(j, i);
            }
            result[i] = String.format(TEMPLATE, i, threadSum);
        }
        return result;
    }
}
