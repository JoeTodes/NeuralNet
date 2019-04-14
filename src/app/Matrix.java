package app;

public class Matrix {
    int rows, cols;
    double[][] matrix;

    public Matrix(int rows_, int cols_) {
        rows = rows_;
        cols = cols_;

        matrix = new double[rows][cols];
    }

    public void scMult(double n) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] *= n;
            }
        }
    }

    public void scAdd(double n) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] += n;
            }
        }
    }

    public void elemAdd(Matrix m) {
        if ((this.rows == m.rows) && (this.cols == m.cols)) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    this.matrix[i][j] += m.matrix[i][j];
                }
            }
        } else {
            System.err.println("attempting to add two matrices of different size");
        }
    }

    public void elemMult(Matrix m) {
        if ((this.rows == m.rows) && (this.cols == m.cols)) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    this.matrix[i][j] *= m.matrix[i][j];
                }
            }
        } else {
            System.err.println("attempting to multiply two matrices of different size");
        }
    }

    public static Matrix product(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.rows, b.cols);
        if (a.cols == b.rows) {
            for (int i = 0; i < result.rows; i++) {
                for (int j = 0; j < result.cols; j++) {
                    double sum = 0;
                    for (int k = 0; k < a.cols; k++) {
                        sum += a.matrix[i][k] * b.matrix[k][j];
                    }
                    result.matrix[i][j] = sum;
                }
            }
        } else {
            System.err.println("attempting to perform matrix products on bad sizes");
        }
        return result;
    }

    public static Matrix transpose(Matrix a) {
        Matrix result = new Matrix(a.cols, a.rows);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.matrix[i][j] = a.matrix[j][i];
            }
        }
        return result;
    }

    public void randomizeInt() {

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = Math.floor((Math.random() - 0.5) * 20);
            }
        }
    }

}