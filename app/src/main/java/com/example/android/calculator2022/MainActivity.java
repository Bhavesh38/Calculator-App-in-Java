package com.example.android.calculator2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultText,solutionText;
    MaterialButton buttonC,buttonOpenBracket,buttonCloseBracket,buttonAC,buttonDot;
    MaterialButton buttonDivide,buttonMutliply,buttonMinus,buttonPlus,buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText=findViewById(R.id.result_text);
        solutionText=findViewById(R.id.solution_text);

        assignId(buttonC,R.id.button_c);
        assignId(buttonOpenBracket,R.id.button_openBracket);
        assignId(buttonCloseBracket,R.id.button_closeBracket);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMutliply,R.id.button_multiply);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonEqual,R.id.button_equal);
        assignId(button0,R.id.button_zero);
        assignId(button1,R.id.button_one);
        assignId(button2,R.id.button_two);
        assignId(button3,R.id.button_three);
        assignId(button4,R.id.button_four);
        assignId(button5,R.id.button_five);
        assignId(button6,R.id.button_six);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.button_eight);
        assignId(button9,R.id.button_nine);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;

        String buttonText=button.getText().toString();


        String dataToCalculate=solutionText.getText().toString();

        if(buttonText.equals("AC")){
            solutionText.setText("0");
            resultText.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionText.setText(resultText.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate+=buttonText;
        }


        solutionText.setText(dataToCalculate);

        String ans=getResult(dataToCalculate);
        if(!ans.equals("Err")){
            resultText.setText(ans);
        }
    }

    String getResult(String data ){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String ans=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(ans.endsWith(".0")){
                ans=ans.replace(".0","");
            }
            return ans;
        }catch (Exception e){
            return "Err";
        }
    }
}