package entity;

public class MatrixThread {
//    todo inherit threads entity
//    todo make static variable to set index name
    private int index;

    public MatrixThread(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "MatrixThread{" +
                "index=" + index +
                '}';
    }
}
