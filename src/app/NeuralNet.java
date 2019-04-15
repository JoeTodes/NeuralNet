package app;

public class NeuralNet {
    int inputNodes, hiddenNodes, outputNodes;
    Matrix weightsIH, weightsHO, biasH, biasO;

    public NeuralNet(int numIn, int numHidden, int numOut) {
        this.inputNodes = numIn;
        this.hiddenNodes = numHidden;
        this.outputNodes = numOut;

        this.weightsIH = new Matrix(this.hiddenNodes, this.inputNodes);
        this.weightsHO = new Matrix(this.outputNodes, this.hiddenNodes);

        this.weightsIH.randomizeDouble(-1, 1);
        this.weightsHO.randomizeDouble(-1, 1);

        this.biasH = new Matrix(this.hiddenNodes, 1);
        this.biasO = new Matrix(this.outputNodes, 1);
        this.biasH.randomizeDouble(-1, 1);
        this.biasO.randomizeDouble(-1, 1);

    }

    public double[] feedforward(double[] input) {
        Matrix inputs = Matrix.fromArray(input);

        Matrix hidden = Matrix.product(this.weightsIH, inputs); // matrix multiply weights with inputs
        hidden.elemAdd(this.biasH); // add in bias
        hidden.map(x -> sigmoid(x)); // activation function

        Matrix output = Matrix.product(this.weightsHO, hidden);
        output.elemAdd(this.biasO);
        output.map(x -> sigmoid(x));

        return output.toArray();
    }

    double sigmoid(double in) {
        return 1 / (1 + Math.exp(-in));
    }

    public void train(double[] inputs, double[] targets) {
        Matrix outputs = Matrix.fromArray(feedforward(inputs));
        Matrix answers = Matrix.fromArray(targets);

        // calculate output error
        Matrix outErrors = Matrix.elemSub(answers, outputs);

        // calculate hidden layer errors
        Matrix hiddenErrors = Matrix.product(Matrix.transpose(this.weightsHO), outErrors);

        // calculate input layer errors
        Matrix inputErrors = Matrix.product(Matrix.transpose(this.weightsIH), hiddenErrors);

    }
}