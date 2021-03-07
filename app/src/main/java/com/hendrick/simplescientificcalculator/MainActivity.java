package com.hendrick.simplescientificcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String PERSIST_STATE = "persist_state";
    public static String HISTORY_DATA = "history_expressions";
    private String COUNT_STATE = "count";
    private Button mClearBtn;
    private Button mHistoryBtn;
    private Button mEqualBtn;
    private Button mOneButton;
    private Button mTwoButton;
    private Button mThreeButton;
    private Button mFourButton;
    private Button mFiveButton;
    private Button mSixButton;
    private Button mSevenButton;
    private Button mEightButton;
    private Button mNineButton;
    private Button mZeroButton;
    private Button mDecimalButton;
    private Button mAddButton;
    private Button mSubtractButton;
    private Button mDivideButton;
    private Button mMultiplyButton;
    private Button mAddSubtractButton;
    private Button mNRootXBtn;
    private Button mRootXBtn;
    private Button mXRaisedToNBtn;
    private Button mXSquareBtn;
    private Button mNaturalLogBtn;
    private Button mLogBtn;
    private TextView mCalcTV;
    private HistoryHelper mHistoryHelper;
    private double firstArgument;
    private double secondArgument;
    private int count;
    private boolean operatorCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHistoryHelper = HistoryHelper.getInstance();
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            initializeDisplayContent();
            defaultButtonConfiguration();
        } else
            initializeDisplayContent();
        buttonState(false);
        buttonClicks();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PERSIST_STATE, mCalcTV.getText().toString());
        outState.putInt(COUNT_STATE, count);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCalcTV.setText(savedInstanceState.getString(PERSIST_STATE));
        count = savedInstanceState.getInt(COUNT_STATE);
    }

    private void initializeDisplayContent() {
        // Calculator TextView
        mCalcTV = findViewById(R.id.calcTV);

        // other buttons
        mClearBtn = findViewById(R.id.clearBtn);
        mHistoryBtn = findViewById(R.id.historyBtn);
        mEqualBtn = findViewById(R.id.equalBtn);

        // number buttons
        mOneButton = findViewById(R.id.oneBtn);
        mTwoButton = findViewById(R.id.twoBtn);
        mThreeButton = findViewById(R.id.threeBtn);
        mFourButton = findViewById(R.id.fourBtn);
        mFiveButton = findViewById(R.id.fiveBtn);
        mSixButton = findViewById(R.id.sixBtn);
        mSevenButton = findViewById(R.id.sevenBtn);
        mEightButton = findViewById(R.id.eightBtn);
        mNineButton = findViewById(R.id.nineBtn);
        mZeroButton = findViewById(R.id.zeroBtn);
        mDecimalButton = findViewById(R.id.decimalBtn);

        // operators buttons
        mAddButton = findViewById(R.id.addBtn);
        mSubtractButton = findViewById(R.id.subtractBtn);
        mDivideButton = findViewById(R.id.divideBtn);
        mMultiplyButton = findViewById(R.id.multiplyBtn);
        mAddSubtractButton = findViewById(R.id.addSubtractBtn);

        // other special operators
        mNRootXBtn = findViewById(R.id.nRootXBtn);
        mRootXBtn = findViewById(R.id.rootXBtn);
        mXRaisedToNBtn = findViewById(R.id.xRaiseToNBtn);
        mXSquareBtn = findViewById(R.id.xSquareBtn);
        mNaturalLogBtn = findViewById(R.id.naturalLogBtn);
        mLogBtn = findViewById(R.id.logBtn);
    }

    private void defaultButtonConfiguration(){
        mNRootXBtn.setVisibility(View.GONE);
        mRootXBtn.setVisibility(View.GONE);
        mXRaisedToNBtn.setVisibility(View.GONE);
        mXSquareBtn.setVisibility(View.GONE);
        mNaturalLogBtn.setVisibility(View.GONE);
        mLogBtn.setVisibility(View.GONE);
    }

    public void buttonClicks(){
        mOneButton.setOnClickListener(this);
        mTwoButton.setOnClickListener(this);
        mThreeButton.setOnClickListener(this);
        mFourButton.setOnClickListener(this);
        mFiveButton.setOnClickListener(this);
        mSixButton.setOnClickListener(this);
        mSevenButton.setOnClickListener(this);
        mEightButton.setOnClickListener(this);
        mNineButton.setOnClickListener(this);
        mZeroButton.setOnClickListener(this);
        mDecimalButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);
        mSubtractButton.setOnClickListener(this);
        mMultiplyButton.setOnClickListener(this);
        mDivideButton.setOnClickListener(this);
        mAddSubtractButton.setOnClickListener(this);
        mEqualBtn.setOnClickListener(this);
        mClearBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);
        mNRootXBtn.setOnClickListener(this);
        mRootXBtn.setOnClickListener(this);
        mXRaisedToNBtn.setOnClickListener(this);
        mXSquareBtn.setOnClickListener(this);
        mNaturalLogBtn.setOnClickListener(this);
        mLogBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mOneButton) {
            input("1");
            buttonState(true);
        }
        else if (v == mTwoButton) {
            input("2");
            buttonState(true);
        }
        else if (v == mThreeButton) {
            input("3");
            buttonState(true);
        }
        else if (v == mFourButton) {
            input("4");
            buttonState(true);
        }
        else if (v == mFiveButton) {
            input("5");
            buttonState(true);
        }
        else if (v == mSixButton) {
            input("6");
            buttonState(true);
        }
        else if (v == mSevenButton) {
            input("7");
            buttonState(true);
        }
        else if (v == mEightButton) {
            input("8");
            buttonState(true);
        }
        else if (v == mNineButton) {
            input("9");
            buttonState(true);
        }
        else if (v == mZeroButton) {
            input("0");
            buttonState(true);
        }
        else if (v == mDecimalButton) {
            buttonState(true);
            if (mCalcTV.getText().toString().contains(".")){
                mDecimalButton.setEnabled(false);
            }else
                input(".");
        }else if (v == mAddButton){
            if (mCalcTV.getText().toString().contains("^")){
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0,mCalcTV.getText().toString().indexOf("^")));
                mHistoryHelper.addToExpression("^");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("^")+1));
            } else if (mCalcTV.getText().toString().contains("\u221A")){
                if(mCalcTV.getText().toString()
                        .substring(0,mCalcTV.getText().toString().indexOf("\u221A")).isEmpty())
                    mHistoryHelper.addToExpression("2");
                else
                    mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0, mCalcTV.getText().toString().indexOf("\u221A")));
                mHistoryHelper.addToExpression("\u221A");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("\u221A")+1));
            } else
                mHistoryHelper.addToExpression(mCalcTV.getText().toString());
            buttonState(false);
            mHistoryHelper.addToExpression("+");
            mCalcTV.setText("");
        } else if (v == mSubtractButton){
            if (mCalcTV.getText().toString().contains("^")){
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0,mCalcTV.getText().toString().indexOf("^")));
                mHistoryHelper.addToExpression("^");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("^")+1));
            } else if (mCalcTV.getText().toString().contains("\u221A")){
                if(mCalcTV.getText().toString()
                        .substring(0,mCalcTV.getText().toString().indexOf("\u221A")).isEmpty())
                    mHistoryHelper.addToExpression("2");
                else
                    mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0, mCalcTV.getText().toString().indexOf("\u221A")));
                mHistoryHelper.addToExpression("\u221A");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("\u221A")+1));
            }else
                mHistoryHelper.addToExpression(mCalcTV.getText().toString());
            buttonState(false);
            mHistoryHelper.addToExpression("-");
            mCalcTV.setText("");
        } else if (v == mDivideButton){
            if (mCalcTV.getText().toString().contains("^")){
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0,mCalcTV.getText().toString().indexOf("^")));
                mHistoryHelper.addToExpression("^");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("^")+1));
            } else if (mCalcTV.getText().toString().contains("\u221A")){
                if(mCalcTV.getText().toString()
                        .substring(0,mCalcTV.getText().toString().indexOf("\u221A")).isEmpty())
                    mHistoryHelper.addToExpression("2");
                else
                    mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0, mCalcTV.getText().toString().indexOf("\u221A")));
                mHistoryHelper.addToExpression("\u221A");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("\u221A")+1));
            }else
                mHistoryHelper.addToExpression(mCalcTV.getText().toString());
            buttonState(false);
            mHistoryHelper.addToExpression("\u00F7");
            mCalcTV.setText("");
        } else if (v == mMultiplyButton){
            if (mCalcTV.getText().toString().contains("^")){
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0,mCalcTV.getText().toString().indexOf("^")));
                mHistoryHelper.addToExpression("^");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("^")+1));
            } else if (mCalcTV.getText().toString().contains("\u221A")){
                if(mCalcTV.getText().toString()
                        .substring(0,mCalcTV.getText().toString().indexOf("\u221A")).isEmpty())
                    mHistoryHelper.addToExpression("2");
                else
                    mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0, mCalcTV.getText().toString().indexOf("\u221A")));
                mHistoryHelper.addToExpression("\u221A");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("\u221A")+1));
            }else
                mHistoryHelper.addToExpression(mCalcTV.getText().toString());
            buttonState(false);
            mHistoryHelper.addToExpression("*");
            mCalcTV.setText("");
        } else if (v == mAddSubtractButton){
            mCalcTV.setText(mCalcTV.getText().toString().contains("-") ?
                    mCalcTV.getText().toString().substring(1) : "-"+mCalcTV.getText().toString());
        }// operations for the buttons that shows in landscape mode
        else if (v == mRootXBtn){
            buttonState(true);
            mCalcTV.setText("\u221A" + mCalcTV.getText().toString());
            specialButtonState(false);
        } else if (v == mNRootXBtn){
            buttonState(true);
            input("\u221A");
        } else if (v == mXRaisedToNBtn){
            buttonState(true);
//            mHistoryHelper.addToExpression(mCalcTV.getText().toString());
//            mHistoryHelper.addToExpression("^");
            input("^");
        } else if (v == mXSquareBtn){
            buttonState(true);
//            mHistoryHelper.addToExpression(mCalcTV.getText().toString());
//            mHistoryHelper.addToExpression("^");
            input("^2");
            specialButtonState(false);
        } else if (v == mLogBtn){
            buttonState(true);
            mCalcTV.setText("log(" + mCalcTV.getText().toString() + ")");
            specialButtonState(false);
        } else if (v == mNaturalLogBtn){
            buttonState(true);
            mCalcTV.setText("ln(" + mCalcTV.getText().toString() + ")");
            specialButtonState(false);
        } else if (v == mEqualBtn){
            buttonState(true);
            if (mCalcTV.getText().toString().contains("^")){
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0,mCalcTV.getText().toString().indexOf("^")));
                mHistoryHelper.addToExpression("^");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("^")+1));
            } else if (mCalcTV.getText().toString().contains("\u221A")){
                if(mCalcTV.getText().toString()
                        .substring(0,mCalcTV.getText().toString().indexOf("\u221A")).isEmpty())
                    mHistoryHelper.addToExpression("2");
                else
                    mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(0, mCalcTV.getText().toString().indexOf("\u221A")));
                mHistoryHelper.addToExpression("\u221A");
                mHistoryHelper.addToExpression(mCalcTV.getText().toString().substring(mCalcTV.getText().toString().indexOf("\u221A")+1));
            } else
                mHistoryHelper.addToExpression(mCalcTV.getText().toString());
            mHistoryHelper.addToList();
            mHistoryHelper.reset();
            mCalcTV.setText("");

            if (mHistoryHelper.returnOperationbyIndex(count).contains("log")){
                String expression = mHistoryHelper.returnOperationbyIndex(count);
                double argument = Double.parseDouble(expression.substring(expression.indexOf("(")+1, expression.indexOf(")")));
                mCalcTV.setText(Double.toString(Math.log10(argument)));
                return;
            } else if (mHistoryHelper.returnOperationbyIndex(count).contains("ln")){
                String expression = mHistoryHelper.returnOperationbyIndex(count);
                double argument = Double.parseDouble(expression.substring(expression.indexOf("(")+1, expression.indexOf(")")));
                mCalcTV.setText(Double.toString(Math.log10(argument)));
                return;
            }

            doCalculation(mHistoryHelper.returnOperationbyIndex(count));
            count++;
        }else if (v == mClearBtn){
            mCalcTV.setText("");
            buttonState(true);
            mHistoryHelper.reset();
        } // passing the intent when the history button is pressed
        else if (v == mHistoryBtn){
            Intent intent = new Intent(MainActivity.this, CalculationHistory.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ARRAYLIST", (Serializable) mHistoryHelper.returnOperationsList());
            intent.putExtra(HISTORY_DATA, bundle);
            startActivity(intent);
        }
    }

    private void doCalculation(String inputString) {

        AsyncTask task = new AsyncTask() {
            String result;
            @Override
            protected Object doInBackground(Object[] objects) {
                StringInputToFloatResultParser stringInputToFloatResultParser = null;
                try {
                    stringInputToFloatResultParser = new StringInputToFloatResultParser(inputString);
                    result = stringInputToFloatResultParser.toString();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mCalcTV.setText(result);
            }
        };

        task.execute();
    }

    private void buttonState(boolean enableState) {
        mAddButton.setEnabled(enableState);
        mSubtractButton.setEnabled(enableState);
        mDivideButton.setEnabled(enableState);
        mMultiplyButton.setEnabled(enableState);
        mRootXBtn.setEnabled(enableState);
        mXRaisedToNBtn.setEnabled(enableState);
        mNRootXBtn.setEnabled(enableState);
        mXSquareBtn.setEnabled(enableState);
        mLogBtn.setEnabled(enableState);
        mNaturalLogBtn.setEnabled(enableState);
    }

    private void specialButtonState(boolean state){
        mRootXBtn.setEnabled(state);
        mXRaisedToNBtn.setEnabled(state);
        mNRootXBtn.setEnabled(state);
        mXSquareBtn.setEnabled(state);
        mLogBtn.setEnabled(state);
        mNaturalLogBtn.setEnabled(state);
    }

    private void input(String s) {
        String original = mCalcTV.getText().toString();
        mCalcTV.setText(original+s);
    }
}