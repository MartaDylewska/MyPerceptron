package com.machineLearing;

import java.util.Random;

public class Perceptron {
    //Attribute
    private double[] weights;
    private double bias;

    Perceptron(int inputsNo) {
        Random random = new Random();
        //initialize weights
        this.weights = new double[inputsNo];
        for (int i = 0; i < inputsNo; i++) {
            this.weights[i] = random.nextDouble();
        }
        //initialize bias
        this.bias = random.nextDouble();
    }

    public double calculateOutput(double[] input) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i] * this.weights[i];
        }
        sum += this.bias;
        //return sum;
        return activationFunction(sum, 1);
    }

    public double activationFunction(double sum, int actFuncType) {
        if (actFuncType == 1) { //step functions
            if (sum > 0)
                return 1;
            else
                return 0;
        }
        return -1;
    }

    public void train(double[][] inputs, double[] targets, double lRate, int maxEpoch) {
        int weightsNo = inputs[0].length;
        int trainingNo = targets.length;
        double delta_w = 0;

        double delta_b = 0;
        double sumError = 0;

        for (int l = 0; l < maxEpoch; l++) { //each iteration
            for (int j = 0; j < weightsNo; j++) { //each weight
                delta_b = 0;
                delta_w = 0;
                sumError = 0;
                for (int i = 0; i < trainingNo; i++) { //each training sample
                    double o_i = this.calculateOutput(inputs[i]);
                    //calculate the error
                    double error = targets[i] - o_i;
                    sumError += error;
                    //calculate the delta_w
                    delta_w += error * inputs[i][j];
                }

                //update the j-th weight
                //waga_nowa = waga_stara +szybkość uczenia*(wartość_oczekiwana - wartość otrzymana)*wejscie
                this.weights[j] += lRate * delta_w;

                for (int i = 0; i < trainingNo; i++) { //each training sample
                    double o_i = this.calculateOutput(inputs[i]);
                    //calculate the error
                    double error = targets[i] - o_i;
                    delta_b += error * 1;
                }
                //update the bias
                this.bias += lRate * delta_b;


            }
            System.out.println("Iteration #" + l
                    + " w1 = " + this.weights[0]
                    + "w2 = " + this.weights[1]
                    + " b = " + this.bias
                    + " sum error = " + sumError);
        }
    }
}
