package com.cschool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double[][] entryDataTab = getEntranceData("src\\main\\resources\\train.txt");
        double[] outputDataTab = getOutputData("src\\main\\resources\\train.txt");
        double lRate = 0.8;
        int maxEpoch = 100;
/*        for(int i = 0; i<entryDataTab.length;i++){
            System.out.println(Arrays.toString(entryDataTab[i]));
        }*/
        Perceptron perceptron = new Perceptron(2);
        //training the perceptron
        perceptron.train(entryDataTab,outputDataTab,lRate,maxEpoch);

        //testing the perceptron
        double[][] entryTestTab = getEntranceData("src\\main\\resources\\test.txt");
        double[] outputTestTab = getOutputData("src\\main\\resources\\test.txt");
        for(int i = 0; i<outputTestTab.length;i++){
            double calcOutput = perceptron.calculateOutput(entryTestTab[i]);
            if(calcOutput!=outputTestTab[i]){
                System.out.println("false");
            }
        }


    }
    private static double[][] getEntranceData(String filepath){
        List<String> entryData = getListOfData(filepath);
        double[][] entryTab = new double[entryData.size()][2];
        for(int i = 0; i<entryTab.length;i++){
            String[] tempTab = entryData.get(i).split(",");
            entryTab[i][0] = Double.parseDouble(tempTab[0]);
            entryTab[i][1] = Double.parseDouble(tempTab[1]);
        }
        return entryTab;
    }

    private static double[] getOutputData(String filepath){
        List<String> entryData = getListOfData(filepath);
        double[] outputTab = new double[entryData.size()];
        for(int i = 0; i<outputTab.length;i++){
            String[] tempTab = entryData.get(i).split(",");
            outputTab[i] = Double.parseDouble(tempTab[2]);
        }
        return outputTab;
    }

    private static List<String> getListOfData(String filepath){
        List<String> entryData = new ArrayList<>();
        Scanner s = null;
        try {s = new Scanner(new File(filepath));}
        catch (FileNotFoundException e) {e.printStackTrace();}

        while (s.hasNext()){entryData.add(s.next());}
        s.close();
        return entryData;
    }
}
