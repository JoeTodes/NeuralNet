package app;

import processing.core.PApplet;

public class App extends PApplet {
    double[][] inputArr = { { 1, 0 }, { 1, 1 }, { 0, 0 }, { 0, 1 } };
    double[][] targetArr = { { 1 }, { 0 }, { 0 }, { 1 } };
    NeuralNet nn;
    int iterations;

    public static void main(String[] args) {
        PApplet.main("app.App"); // must match this file name and package
    }

    public void settings() { // only used for setting the size of the canvas
        size(640, 480);
    }

    public void setup() { // runs once on launch
        // net = new NeuralNet(2, 2, 1, 0.3);
        int[] config = { 2, 6, 1 };
        nn = new NeuralNet(config, 0.1);
        // nn.feedforward(this.inputArr[0]);

    }

    public void draw() { // main loop
        for (int j = 0; j < 100; j++) {
            int index = floor(random(0, 4));
            nn.train(this.inputArr[index], this.targetArr[index]);
            iterations += 1;
        }

        loadPixels();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i + width * j] = color(51);
                double mappedX = map(i, 0, this.width, 0, 1);
                double mappedY = map(j, 0, this.height, 0, 1);
                double[] pix = { mappedX, mappedY };
                double[] result = nn.guess(pix);
                pixels[i + (width * j)] = color(floor(map((float) result[0], 0, 1, 0, 255)));
            }
        }
        updatePixels();

        textSize(50);
        fill(255);
        text(iterations, 0, 50);
    }
}