package by.training.task3.util;

import by.training.task3.entity.Matrix;
import by.training.task3.exception.MatrixIndexOutBoundException;
import by.training.task3.thread.MatrixWorker;
import org.apache.log4j.Logger;

public class MatrixSummator {
    private static final Logger LOGGER = Logger.getLogger(MatrixSummator.class);
    static final String TEMPLATE = "Thread %d sum %d\n";

    public String getFullSum() throws MatrixIndexOutBoundException{
        Matrix matrix = Matrix.getInstance();
        StringBuilder result = new StringBuilder();
        int threadSum;
        for (int i = 0; i < matrix.getSize(); i++) {
            threadSum = 0;
            for (int j = 0; j < matrix.getSize(); j++) {
                try {
                    threadSum += matrix.getElementValue(i, j);
                    threadSum += matrix.getElementValue(j, i);
                } catch (MatrixIndexOutBoundException e) {
                    LOGGER.warn(e);
                }
            }
            result.append(String.format(TEMPLATE, matrix.getElementValue(i, i), threadSum));
        }
        result.append("\n");
        return result.toString();
    }
}
