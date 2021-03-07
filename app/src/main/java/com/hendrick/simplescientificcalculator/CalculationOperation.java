package com.hendrick.simplescientificcalculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculationOperation {
    static DecimalFormat df = new DecimalFormat("#.#########");

    private CalculationOperation(){ df.setRoundingMode(RoundingMode.CEILING);}

    public static String add(double argumentOne, double argumentTwo){
        return df.format(argumentOne + argumentTwo);
    }

    public static String subtract(double argumentOne, double argumentTwo){
        return df.format(argumentOne - argumentTwo);
    }

    public static String divide(double argumentOne, double argumentTwo){
        return df.format(argumentOne/argumentTwo);
    }

    public static String multiply(double argumentOne, double argumentTwo){
        return df.format(argumentOne*argumentTwo);
    }

    public static String logarithm(double argument){
        return df.format(Math.log10(argument));
    }

    public static String naturalLog(double argument){
        return df.format(Math.log(argument));
    }

    public static String root(double argument, int nthRoot){
        return df.format(Math.pow(argument, (double) 1/nthRoot));
    }

    public static String power(double argument, int nthPow){
        return df.format(Math.pow(argument, nthPow));
    }
}
