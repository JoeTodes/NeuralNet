package app;

import processing.core.PApplet;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("app.App"); // must match this file name and package
    }

    public void settings() { // only used for setting the size of the canvas
        size(640, 480);
    }

    public void setup() { // runs once on launch
        NeuralNet net = new NeuralNet(5, 3, 2);
        double[] inputs = { -2, -3, -2, 0, 8 };
        double[] targets = { 1, 0 };
        // double[] outputs = net.feedforward(inputs);
        net.train(inputs, targets);
    }

    public void draw() { // main loop

    }
}