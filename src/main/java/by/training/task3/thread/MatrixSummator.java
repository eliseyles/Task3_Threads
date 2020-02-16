package by.training.task3.thread;

import by.training.task3.entity.Matrix;
import by.training.task3.exception.MatrixIndexOutBoundException;

public class MatrixSummator {
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
//                    todo log
                }
            }
            result.append(String.format(TEMPLATE, matrix.getElementValue(i, i), threadSum));
        }
        return result.toString();
    }
}
