package com.hendrick.simplescientificcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CalculationHistory extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_history);
        ListView listView = findViewById(R.id.historyLV);

        Intent intent = getIntent();
        Bundle historyBundle = intent.getBundleExtra(MainActivity.HISTORY_DATA);
        ArrayList<String> operationHistory = (ArrayList<String>)
                historyBundle.getSerializable("ARRAYLIST");
//        ArrayList<String> history = new ArrayList<>();
//        history.add("First Expression");
//        history.add("Second Expression");
//        history.add("Third Expression");

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.history_item, R.id.item, operationHistory);
        listView.setAdapter(adapter);
    }
}