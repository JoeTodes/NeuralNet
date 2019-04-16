package app;

import java.util.Arrays;

public class Matrix {
    int rows, cols;
    double[][] data;

    public Matrix(int rows_, int cols_) {
        rows = rows_;
        cols = cols_;

        data = new double[rows][cols];
    }

    public void print() {
        System.out.println(Arrays.deepToString(this.data));
    }

    public void elemAdd(Matrix m) {
        if ((this.rows == m.rows) && (this.cols == m.cols)) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    this.data[i][j] += m.data[i][j];
                }
            }
        } else {
            System.err.println("attempting to add two matrices of different size");
        }
    }

    public static Matrix elemSub(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.rows, a.cols);
        if ((a.rows == b.rows) && (a.cols == b.cols)) {
            for (int i = 0; i < result.rows; i++) {
                for (int j = 0; j < result.cols; j++) {
                    result.data[i][j] = a.data[i][j] - b.data[i][j];
                }
            }
        } else {
            System.err.println("attempting to perform subtraction on bad sizes");
        }
        return result;
    }

    public void elemMult(Matrix m) {
        if ((this.rows == m.rows) && (this.cols == m.cols)) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    this.data[i][j] *= m.data[i][j];
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
                        sum += a.data[i][k] * b.data[k][j];
                    }
                    result.data[i][j] = sum;
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
                result.data[i][j] = a.data[j][i];
            }
        }
        return result;
    }

    public void map(Func map) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double val = data[i][j];
                data[i][j] = map.apply(val);
            }
        }
    }

    public static Matrix map(Matrix m, Func map) {
        Matrix result = new Matrix(m.rows, m.cols);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                double val = m.data[i][j];
                result.data[i][j] = map.apply(val);
            }
        }
        return result;
    }

    @FunctionalInterface
    public interface Func {
        double apply(double val);
    }

    public void randomizeInt(double min, double max) {
        double range = max - min;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] = Math.floor((Math.random() * range) + min);
            }
        }
    }

    public void randomizeDouble(double min, double max) {
        double range = max - min;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] = ((Math.random() * range) + min);
            }
        }
    }

    public static Matrix fromArray(double[] input) {
        int len = input.length;
        Matrix result = new Matrix(len, 1);
        for (int i = 0; i < len; i++) {
            result.data[i][0] = input[i];
        }
        return result;
    }

    public double[] toArray() {
        double[] result = new double[this.rows * this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i * this.cols + j] = this.data[i][j];
            }
        }
        return result;
    }

}