package app;

import java.util.Arrays;

import processing.core.PApplet;

public class App extends PApplet {
    NeuralNet net;
    Matrix mat;

    public static void main(String[] args) {
        PApplet.main("app.App"); // must match this file name and package
    }

    public void settings() { // only used for setting the size of the canvas
        size(640, 480);
    }

    public void setup() { // runs once on launch
        net = new NeuralNet(3, 3, 1);
        mat = new Matrix(2, 3);
        mat.randomizeInt();
        Matrix mat2 = new Matrix(3, 1);
        mat2.randomizeInt();
        System.out.println(Arrays.deepToString(mat.matrix));
        Matrix mat3 = Matrix.transpose(mat);
        System.out.println(Arrays.deepToString(mat3.matrix));
    }

    public void draw() { // main loop

    }
}