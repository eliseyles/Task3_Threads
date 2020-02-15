package by.training.task3.thread;

import by.training.task3.entity.Matrix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.FutureTask;

public class WriterThread extends Thread {

    private File file;

    public WriterThread(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        Matrix matrix = Matrix.getInstance();
        matrix.unlockAll();
        if (file != null) {
            try  {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                bufferedWriter.write(matrix.toString());
                MatrixSummator matrixSummator = new MatrixSummator();
                FutureTask futureTask = new FutureTask(matrixSummator);
                Thread summator = new Thread();
                summator.start();
                summator.join();
                String[] result = (String[]) futureTask.get();
                for (int i = 0; i < result.length; i++) {
                    bufferedWriter.write(result[i]);
                }

            } catch (IOException e) {
//                LOG.warn(e);
            } catch (Exception e) {
//                log
            }

        } else {
//            LOG.warn("No file to write matrix");
        }
    }
}
