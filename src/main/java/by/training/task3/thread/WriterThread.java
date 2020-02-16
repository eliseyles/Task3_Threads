package by.training.task3.thread;

import by.training.task3.entity.Matrix;
import by.training.task3.exception.DataSourceAccessException;
import by.training.task3.exception.MatrixIndexOutBoundException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class WriterThread extends Thread {

    private static final String EMPTY_STR = "";

    private Semaphore semaphore;
    private File file;
    private boolean isClearFile = false;

    public WriterThread(Semaphore semaphore, String fileName) {
        this.semaphore = semaphore;
        this.file = new File(fileName);
    }

    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (!isClearFile) {
                clearOutFile();
            }
            Matrix matrix = Matrix.getInstance();
            matrix.resetUsed();
            writer.append(matrix.toString());
            writer.append(new MatrixSummator().getFullSum());
        } catch (DataSourceAccessException e) {
//log
        } catch (IOException e) {
//log
        } catch (MatrixIndexOutBoundException e) {
//        todo log
        } finally {
            semaphore.release();
        }
    }

    public void clearOutFile() throws DataSourceAccessException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(EMPTY_STR);
            isClearFile = true;
        } catch (IOException e) {
            throw new DataSourceAccessException(e);
        }
    }
}
