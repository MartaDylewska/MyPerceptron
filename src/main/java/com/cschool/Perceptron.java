package com.cschool;

import java.util.Random;

public class Perceptron {

    // Attribute
    private double[] weights;
    private double bias;

    Perceptron(int inputsNo){
        Random r = new Random();
        this.weights = new double[inputsNo];
        // Initialize weights
        for (int i = 0 ; i < inputsNo ; i++ ) {
            this.weights[i] = r.nextDouble() ;
        }
        // Initialize bias
        this.bias = r.nextDouble();
    }


    public double calculateOutput(double[] input) {
        double sum = 0;
        for (int i = 0 ; i < this.weights.length ; i++) {
            sum = sum + input[i] * this.weights[i];
        }
        sum = sum + this.bias;
        return activationFunction(sum,1);
    }

    public double activationFunction(double sum, int actFuncType) {
        if (actFuncType == 1) { // Step function
            if (sum > 0) {
                return 1;
            }
            else {
                return 0;
            }
        }

        return -1;
    }


    public void train(double[][] inputs, double[] targets
            , double lrate, int maxEpoch) {


        int weightsNo = inputs[0].length;
        int trainingNo = targets.length;

        double delta_w = 0;
        double delta_b = 0;
        double sumError= 0;

       // for (int l = 0 ; l < maxEpoch ; l++) { // each iteration
        int l = 0;
        do{
            for(int j = 0 ; j < weightsNo ; j++ ) { // each weight

                delta_w = 0;
                delta_b = 0;
                sumError= 0;
                for(int i = 0 ; i < trainingNo ; i++ ) { // each training samples
                    double o_i = this.calculateOutput(inputs[i]);

                    // calculate the error
                    double error = targets[i] - o_i;

                    sumError += error;

                    // Calculate the delta_j
                    delta_w += error * inputs[i][j];}

                // Update the j-th weight
                this.weights[j] += lrate * delta_w;
                for(int i = 0 ; i < trainingNo ; i++ ) {
                    double o_i = this.calculateOutput(inputs[i]);
                    // calculate the error
                    double error = targets[i] - o_i;

                    delta_b += error * 1;
                }
                // Update the bias
                this.bias += lrate * delta_b;
            }


            System.out.println("Iteration #" + l
                    + " w1 = " + this.weights[0]
                    + " w2 = " + this.weights[1]
                    + " b = " + this.bias
                    + " Sum Error = " + sumError);
            l++;
        } while((sumError != 0.0)&&(l < maxEpoch));

    }




}
