package app;

public class NeuralNet {
    int hiddenLayers, totalLayers, inputNodes, hiddenNodes, outputNodes;
    double learningRate;
    Matrix[] weights, biases, results, errors, gradients, deltaWeights;

    // notes for refactoring for arbitrary #of hiddens:
    // accept as input an array with node numbers
    // make a Matrix[] len=inputarr.length
    // make a Matrix[] of weights, len=inputarr.length-1
    // make a Matrix[] of the biases, length = inputarr.length-1
    public NeuralNet(int numIn, int numHidden, int numOut, double learningRate) {
        /*
         * this.inputNodes = numIn; this.hiddenNodes = numHidden; this.outputNodes =
         * numOut; this.learningRate = learningRate;
         * 
         * this.weightsIH = new Matrix(this.hiddenNodes, this.inputNodes);
         * this.weightsHO = new Matrix(this.outputNodes, this.hiddenNodes);
         * 
         * this.weightsIH.randomizeDouble(-1, 1); this.weightsHO.randomizeDouble(-1, 1);
         * 
         * this.biasH = new Matrix(this.hiddenNodes, 1); this.biasO = new
         * Matrix(this.outputNodes, 1); this.biasH.randomizeDouble(-1, 1);
         * this.biasO.randomizeDouble(-1, 1);
         */

    }

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

    // notes for refactoring for arbitrary #of hiddens:
    // inputs Matrix in nodesArr[0]
    // iterate through to nodesArr.length-2 doing the feedforward
    public void feedforward(double[] input) {
        // inputs = Matrix.fromArray(input);
        this.results[0] = Matrix.fromArray(input);

        for (int i = 0; i < totalLayers - 1; i++) {
            this.results[i + 1] = Matrix.product(this.weights[i], this.results[i]);
            this.results[i + 1].elemAdd(this.biases[i]);
            this.results[i + 1].map(x -> sigmoid(x));
        }

        // return this.results[this.totalLayers - 1].toArray();
        /*
         * hiddens = Matrix.product(this.weightsIH, inputs); // matrix multiply weights
         * with inputs hiddens.elemAdd(this.biasH); // add in bias hiddens.map(x ->
         * sigmoid(x)); // activation function
         * 
         * outputs = Matrix.product(this.weightsHO, hiddens);
         * outputs.elemAdd(this.biasO); outputs.map(x -> sigmoid(x));
         * 
         * return outputs.toArray();
         */
    }

    double sigmoid(double in) {
        return 1 / (1 + Math.exp(-in));
    }

    double unSigmoid(double y) {
        return y * (1 - y);
    }

    // notes for refactoring for arbitrary #of hiddens:
    // after calculating output error, loop backwards through nodes
    public void train(double[] inputs, double[] targets) {
        // Matrix outputs = Matrix.fromArray(feedforward(inputs));
        // Matrix answers = Matrix.fromArray(targets);

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

        /*
         * // calculate output error and adjust weightsHO and biases Matrix outErrors =
         * Matrix.elemSub(answers, outputs); Matrix outGradients = Matrix.map(outputs, x
         * -> unSigmoid(x)); outGradients.elemMult(outErrors); outGradients.map(x -> x *
         * this.learningRate); Matrix dWeightsHO = Matrix.product(outGradients,
         * Matrix.transpose(hiddens)); this.weightsHO.elemAdd(dWeightsHO);
         * this.biasO.elemAdd(outGradients);
         * 
         * // calculate hidden layer errors and adjust weightsIH and biases Matrix
         * hiddenErrors = Matrix.product(Matrix.transpose(this.weightsHO), outErrors);
         * Matrix hiddenGradients = Matrix.map(hiddens, x -> unSigmoid(x));
         * hiddenGradients.elemMult(hiddenErrors); hiddenGradients.map(x -> x *
         * this.learningRate); Matrix dWeightsIH = Matrix.product(hiddenGradients,
         * Matrix.transpose(this.inputs)); this.weightsIH.elemAdd(dWeightsIH);
         * this.biasH.elemAdd(hiddenGradients);
         */
        // calculate input layer errors
        // Matrix inputErrors = Matrix.product(Matrix.transpose(this.weightsIH),
        // hiddenErrors);

        // outErrors.print();
        // System.out.println("done train");
    }
}