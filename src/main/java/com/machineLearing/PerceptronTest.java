package com.machineLearing;

public class PerceptronTest {
    public static void main(String[] args) {
        //System.out.println("");
        double[][] inputs = {{0,0},{0,1},{1,0},{1,1}};
        double[] targets = {0,0,0,1};

        Perceptron perceptron = new Perceptron(inputs[0].length);
        double o = perceptron.calculateOutput(inputs[0]);
        System.out.println("Output " + o);

        double lRate = 0.1;
        int maxEpoch = 15;

        perceptron.train(inputs,targets,lRate,maxEpoch);

        double test[][] = {{0,0},{0,1},{1,0},{1,1}};
        System.out.println(perceptron.calculateOutput(test[0]));
        System.out.println(perceptron.calculateOutput(test[1]));
        System.out.println(perceptron.calculateOutput(test[2]));
        System.out.println(perceptron.calculateOutput(test[3]));
    }
}
