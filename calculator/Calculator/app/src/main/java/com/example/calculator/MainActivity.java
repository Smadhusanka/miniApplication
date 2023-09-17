package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
//import org.mozilla.javascript.Context;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView vSolution, vResult;
    MaterialButton button_C, button_OpenBracket, button_CloseBracket;
    MaterialButton button_Divide, button_Multiply, button_Addtion, button_Substraction, button_Equal;
    MaterialButton button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0;
    MaterialButton button_Ac, button_Point;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vSolution = findViewById(R.id.solution);
        vResult = findViewById(R.id.result);

        assignId(button_C, R.id.buttonC);
        assignId(button_OpenBracket, R.id.buttonOpenBracket);
        assignId(button_CloseBracket, R.id.buttonCloseBracket);
        assignId(button_Divide, R.id.buttonDivide);
        assignId(button_Multiply, R.id.buttonMultiply);
        assignId(button_Addtion, R.id.buttonAddtion);
        assignId(button_Substraction, R.id.buttonSubstraction);
        assignId(button_Equal, R.id.buttonEqual);
        assignId(button_1, R.id.button1);
        assignId(button_2, R.id.button2);
        assignId(button_3, R.id.button3);
        assignId(button_4, R.id.button4);
        assignId(button_5, R.id.button5);
        assignId(button_6, R.id.button6);
        assignId(button_7, R.id.button7);
        assignId(button_8, R.id.button8);
        assignId(button_9, R.id.button9);
        assignId(button_0, R.id.button0);
        assignId(button_Ac, R.id.buttonAc);
        assignId(button_Point, R.id.buttonPoint);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        MaterialButton button = (MaterialButton) v;
        String butttonText = button.getText().toString();
        String dataToCalculate = vSolution.getText().toString();

        if (butttonText.equals("AC")) {
            vSolution.setText("");
            vResult.setText("0");
            return;
        }

        if (butttonText.equals("=")) {
            vSolution.setText(vResult.getText());
            return;
        }

        /*if (butttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        } else {
            dataToCalculate = dataToCalculate+butttonText;
        }*/
        if(butttonText.equals("C")){
            if(dataToCalculate.length() == 1){
                vSolution.setText("");
                vResult.setText("0");
                return;
            }
            else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
            }
        }
        else {
            dataToCalculate = dataToCalculate+butttonText;
        }

        vSolution.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")) {
            vResult.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");

            }
            return finalResult;
        }
            catch(Exception e){
                return "Error";
            }
        }
    }


