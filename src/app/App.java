package app;

import processing.core.PApplet;

public class App extends PApplet {
    double[][] inputArr = { { 1, 0 }, { 1, 1 }, { 0, 0 }, { 0, 1 } };
    double[][] targetArr = { { 1 }, { 0 }, { 0 }, { 1 } };
    NeuralNet net;

    public static void main(String[] args) {
        PApplet.main("app.App"); // must match this file name and package
    }

    public void settings() { // only used for setting the size of the canvas
        size(640, 480);
    }

    public void setup() { // runs once on launch
        // net = new NeuralNet(2, 2, 1, 0.3);
        int[] config = { 2, 6, 1 };
        NeuralNet nn = new NeuralNet(config, 0.1);
        // nn.feedforward(this.inputArr[0]);

        for (int j = 0; j < 100000; j++) {
            int index = floor(random(0, 4));
            nn.train(this.inputArr[index], this.targetArr[index]);
        }

        for (int i = 0; i < 4; i++) {
            nn.feedforward(inputArr[i]);
            nn.results[2].print();
        }

    }

    public void draw() { // main loop

    }
}