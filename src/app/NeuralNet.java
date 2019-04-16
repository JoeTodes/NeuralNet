package app;

public class NeuralNet {
    int hiddenLayers, totalLayers;
    double learningRate;
    Matrix[] weights, biases, results, errors, gradients, deltaWeights;

    public NeuralNet(int[] layers, double learningRate) {
        this.totalLayers = layers.length;
        this.hiddenLayers = this.totalLayers - 2;

        this.learningRate = learningRate;

        this.weights = new Matrix[this.hiddenLayers + 1];
        this.biases = new Matrix[this.hiddenLayers + 1];
        this.results = new Matrix[layers.length];
        this.errors = new Matrix[this.hiddenLayers + 1];
        this.gradients = new Matrix[this.hiddenLayers + 1];
        this.deltaWeights = new Matrix[this.hiddenLayers + 1];

        for (int i = 0; i < this.hiddenLayers + 1; i++) {
            this.weights[i] = new Matrix(layers[i + 1], layers[i]);
            this.weights[i].randomizeDouble(-1, 1);

            this.biases[i] = new Matrix(layers[i + 1], 1);
            this.biases[i].randomizeDouble(-1, 1);
        }
    }

    public void feedforward(double[] input) {
        this.results[0] = Matrix.fromArray(input);

        for (int i = 0; i < totalLayers - 1; i++) {
            this.results[i + 1] = Matrix.product(this.weights[i], this.results[i]);
            this.results[i + 1].elemAdd(this.biases[i]);
            this.results[i + 1].map(x -> sigmoid(x));
        }
    }

    double sigmoid(double in) {
        return 1 / (1 + Math.exp(-in));
    }

    double unSigmoid(double y) {
        return y * (1 - y);
    }

    public void train(double[] inputs, double[] targets) {

        feedforward(inputs);
        Matrix answers = Matrix.fromArray(targets);
        this.errors[this.totalLayers - 2] = Matrix.elemSub(answers, this.results[this.totalLayers - 1]);

        for (int i = this.totalLayers - 2; i >= 0; i--) {

            this.gradients[i] = Matrix.map(this.results[i + 1], x -> unSigmoid(x));
            this.gradients[i].elemMult(this.errors[i]);
            this.gradients[i].map(x -> x * this.learningRate);
            this.deltaWeights[i] = Matrix.product(this.gradients[i], Matrix.transpose(this.results[i]));
            this.weights[i].elemAdd(this.deltaWeights[i]);
            this.biases[i].elemAdd(this.gradients[i]);
            if (i > 0) {
                this.errors[i - 1] = Matrix.product(Matrix.transpose(this.weights[i]), errors[i]);
            }
        }
    }

    public double[] guess(double[] inputs_) {
        double[] result;
        feedforward(inputs_);
        result = this.results[totalLayers - 1].toArray();

        return result;
    }
}