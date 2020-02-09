package entity;

public enum Matrix {
    INSTANCE;

    private int[][] matrix;

    Matrix() {
        this.matrix = new int[0][];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int matrixSize) {
        this.matrix = new int[matrixSize][matrixSize];
    }
}
