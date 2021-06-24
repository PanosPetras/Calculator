package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    public String input;
    public String operator;

    public long value1;
    public long value2;

    public boolean flag;

    public int action;

    public void AddCharacter(View view){
        if(input.length() < 9 || input == "Cannot divide by 0") {
            if (!(input.equals("0")) && operator.indexOf('=') == -1) {
                input = input + view.getTag().toString().charAt(0);
            } else if (view.getTag().toString().charAt(0) != '0') {
                input = Character.toString(view.getTag().toString().charAt(0));
            }
            if(operator.indexOf('=') != -1){
                operator = " ";
                action = 0;
                value1 = 0;
                value2 = 0;
                flag = false;
            }
            UpdateText();
        }
    }

    public void Backspace(View view){
        if(operator.indexOf('=') == -1) {
            if (input.length() > 1) {
                input = input.substring(0, input.length() - 1);
            } else if (input.length() == 1 && !(input.equals("0"))) {
                input = "0";
            }
            UpdateText();
        } else {
            Reset(view);
        }
    }

    public  void Reset(View view){
        input = "0";
        action = 0;
        value1 = 0;
        value2 = 0;
        flag = false;
        operator = " ";
        UpdateText();
    }

    public void PerformAction(View view){
        if(input != "Cannot divide by 0") {
            char act = view.getTag().toString().charAt(0);
            switch (act) {
                case '+':
                    action = 1;
                    break;
                case '-':
                    action = 2;
                    break;
                case '/':
                    action = 3;
                    break;
                case '*':
                    action = 4;
                    break;
                case '%':
                    action = 5;
                    break;
                case '^':
                    action = 6;
                    break;
                case '√':
                    operator = "√" + input + "=";
                    input = Integer.toString((int)(Math.sqrt(Double.parseDouble(input))));
                    break;
            }
            if(act != '√') {
                value1 = Long.parseLong(input);
                operator = value1 + Character.toString(act);
                input = "0";
            }
            flag = false;
            UpdateText();
        }
    }

    public void Result(View view){
        if(flag == false && action != 0) {
            if (input != "Cannot divide by 0") {
                value2 = Integer.parseInt(input);
                switch (action) {
                    case 1:
                        input = Long.toString(value1 + value2);
                        break;
                    case 2:
                        input = Long.toString(value1 - value2);
                        break;
                    case 3:
                        if (value2 != 0) {
                            input = Long.toString(value1 / value2);
                        } else {
                            input = "Cannot divide by 0";
                        }
                        break;
                    case 4:
                        input = Long.toString(value1 * value2);
                        break;
                    case 5:
                        if (value2 != 0) {
                            input = Long.toString(value1 % value2);
                        } else {
                            input = "Cannot divide by 0";
                        }
                        break;
                    case 6:
                        int result = 1;
                        while (value2 != 0) {
                            result *= value1;
                            --value2;
                        }
                        value2 = Integer.parseInt(input);
                        input = Integer.toString(result);
                        break;
                }
                operator += value2 + "=";
                UpdateText();
                flag = true;
            }
        }
    }

    public void UpdateText(){
        // Specify the layout you are using.
        setContentView(R.layout.activity_main);

        // Load and use views afterwards
        TextView tv1 = findViewById(R.id.textView);
        tv1.setText(input);
        TextView tv2 = findViewById(R.id.textView2);
        tv2.setText(operator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        input = "0";
        flag = false;
        operator = " ";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}