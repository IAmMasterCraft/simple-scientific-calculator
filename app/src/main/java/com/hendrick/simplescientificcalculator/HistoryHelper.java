package com.hendrick.simplescientificcalculator;

import java.util.ArrayList;
import java.util.List;

public class HistoryHelper {

    private static HistoryHelper single_instance = null;
    private String expression = "";

    public List<String> operations;
    public List<String> operationals =  new ArrayList<String>();
    int operationalCount;
    private HistoryHelper(){
        operations = new ArrayList<>();
    }

    public static HistoryHelper getInstance(){
        if (single_instance == null){
            single_instance = new HistoryHelper();
        }
        return single_instance;
    }

    public void addToExpression(String operational){
        operationals.add(operationalCount, operational);
        operationalCount++;
    }

    private void createExpression(){
        for (int i = 0; i < operationals.size(); i++){
            expression += " " + operationals.get(i);
        }
    }

    public void addToList(){
        createExpression();
        operations.add(expression);
    }

    public List<String> returnOperationsList(){
        return operations;
    }

    public String returnOperationbyIndex(int index){
        return operations.get(index);
    }

    public void reset(){
        operationalCount = 0;
        expression = "";
        operationals = new ArrayList<String>();
    }

    public void addToList(String s) {
        operations.add(s);
    }
}
