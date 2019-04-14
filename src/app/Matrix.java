package app;

public class Matrix {
    int rows, cols;
    float[][] matrix;

    public Matrix(int rows_, int cols_){
        rows=rows_;
        cols=cols_;

        matrix = new float[rows][cols];
    }

    public void scMult(float n){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                matrix[i][j]*=n;
            }
        }
    }

    public void scAdd(float n){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                matrix[i][j]+=n;
            }
        }
    }

}