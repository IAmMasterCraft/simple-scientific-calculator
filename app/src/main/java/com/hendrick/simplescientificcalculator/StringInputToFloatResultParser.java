package com.hendrick.simplescientificcalculator;

import android.renderscript.FieldPacker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class StringInputToFloatResultParser {
    private static List<Double> arguments;
    private static Stack<Character> operands;
    private Object IllegalArgumentException = null;
    private float mResult;
    private String mOperator;
    private final Random mRand;
    private final List<String> mOperators;

    public StringInputToFloatResultParser(String input) throws Throwable {
        this.arguments = new ArrayList<Double>();
        this.operands = new Stack<>();

        mOperators = new ArrayList<>(Arrays.asList("+", "-", "*", "\u00F7", "^", "\u221A"));
        mRand = new Random();
        mOperator = mOperators.get(mRand.nextInt(mOperators.size()));
        try {
            getArguments(input);
            final int times = operands.size();
            mResult = (float) calculate(times);
        } catch (Exception e){
            throw (Throwable) IllegalArgumentException;
        }
    }

    // Method to calculate the arguments which are placed the arguments ArrayList.
    private double calculate(int times) {
        double result = 0;
        for (int i = 0; i < times; i++){
            Double firstArgs;
            Double secondArgs;
            if (operands.contains('^')){
                firstArgs = arguments.get(operands.indexOf('^'));
                secondArgs = arguments.get(operands.indexOf('^') + 1);
                result = Math.pow(firstArgs, secondArgs);
                arguments.set(operands.indexOf('^'), result); arguments.remove(operands.indexOf('^') + 1);
                operands.remove(operands.indexOf('^'));
            } else if (operands.contains('\u221A')){
                firstArgs = arguments.get(operands.indexOf('\u221A'));
                secondArgs = arguments.get(operands.indexOf('\u221A') + 1);
                result = Math.pow(secondArgs, (double) 1/firstArgs);
                arguments.set(operands.indexOf('\u221A'), result); arguments.remove(operands.indexOf('\u221A') + 1);
                operands.remove(operands.indexOf('\u221A'));
            } else if (operands.contains('\u00F7')){
                firstArgs = arguments.get(operands.indexOf('\u00F7'));
                secondArgs = arguments.get(operands.indexOf('\u00F7') + 1);
                result = firstArgs / secondArgs;
                arguments.set(operands.indexOf('\u00F7'), result); arguments.remove(operands.indexOf('\u00F7') + 1);
                operands.remove(operands.indexOf('\u00F7'));
            } else if (operands.contains('*')){
                firstArgs = arguments.get(operands.indexOf('*'));
                secondArgs = arguments.get(operands.indexOf('*') + 1);
                result = firstArgs * secondArgs;
                arguments.set(operands.indexOf('*'), result); arguments.remove(operands.indexOf('*') + 1);
                operands.remove(operands.indexOf('*'));
            } else if (operands.firstElement() == '+'){
                firstArgs = arguments.get(operands.indexOf('+'));
                secondArgs = arguments.get(operands.indexOf('+') + 1);
                result = firstArgs + secondArgs;
                arguments.set(operands.indexOf('+'), result); arguments.remove(operands.indexOf('+') + 1);
                operands.remove(operands.indexOf('+'));
            } else if (operands.firstElement() == '-'){
                firstArgs = arguments.get(operands.indexOf('-'));
                secondArgs = arguments.get(operands.indexOf('-') + 1);
                result = firstArgs - secondArgs;
                arguments.set(operands.indexOf('-'), result); arguments.remove(operands.indexOf('-') + 1);
                operands.remove(operands.indexOf('-'));
            }
        }

        return result;
    }

    private void getArguments(String input) {
        try{
            Float.parseFloat(input);
            getArguments(input, null);
            return;
        } catch (NumberFormatException e){
            if (input.contains(mOperator)) {
                getArguments(input, mOperator);
                return;
            }
            else {
                mOperators.remove(mOperator);
                mOperator = mOperators.get(mRand.nextInt(mOperators.size()));
                getArguments(input);
            }
        }
    }

    private void getArguments(String input, String operand){
        if (operand == null){
            arguments.add(Double.parseDouble(input));
            return;
        }
        String initialExpression = input.substring(0, input.indexOf(operand));
        try {
            if (input.contains("log")){
                double argument = Double.parseDouble(input.substring(input.indexOf("(") + 1, initialExpression.indexOf(")")));
                arguments.add(Math.log10(argument));
            } else if (input.contains("ln")){
                double argument = Double.parseDouble(input.substring(input.indexOf("(") + 1, initialExpression.indexOf(")")));
                arguments.add(Math.log(argument));
            } else
                arguments.add(Double.parseDouble(initialExpression));
        }
        catch (NumberFormatException e){
            if (initialExpression.contains("-")){
                getArguments(initialExpression, "-");
            }else if (initialExpression.contains("+")){
                getArguments(initialExpression, "+");
            } else if (initialExpression.contains("*")){
                getArguments(initialExpression, "*");
            } else if (initialExpression.contains("\u00F7")){
                getArguments(initialExpression,"\u00F7");
            } else if (initialExpression.contains("^"))
                getArguments(initialExpression, "^");
            else if (arguments.contains("\u221A"))
                getArguments(initialExpression, "\u221A");
        }

        input = input.substring(input.indexOf(operand)+1);
        operands.push(operand.charAt(0));
        getArguments(input);
    }

    @Override
    public String toString() {
        return Float.toString(mResult);
    }
}
