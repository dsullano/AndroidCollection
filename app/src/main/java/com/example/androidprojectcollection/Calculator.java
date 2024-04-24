package com.example.androidprojectcollection;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycalculator);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        // Button initialization and assignment
        initializeButton(R.id.button_c);
        initializeButton(R.id.button_ac);
        initializeButton(R.id.button_OpenBracket);
        initializeButton(R.id.button_closingBracket);
        initializeButton(R.id.button_dot);
        initializeButton(R.id.button_0);
        initializeButton(R.id.button_1);
        initializeButton(R.id.button_2);
        initializeButton(R.id.button_3);
        initializeButton(R.id.button_4);
        initializeButton(R.id.button_5);
        initializeButton(R.id.button_6);
        initializeButton(R.id.button_7);
        initializeButton(R.id.button_8);
        initializeButton(R.id.button_9);
        initializeButton(R.id.button_plus);
        initializeButton(R.id.button_subtract);
        initializeButton(R.id.button_multiply);
        initializeButton(R.id.button_divide);
        initializeButton(R.id.button_equals);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String expression = solutionTv.getText().toString();

        if (buttonText.equals("=")) {
            String result = evaluateExpression(expression);
            resultTv.setText(result);
            solutionTv.setText("0");
        } else if (buttonText.equals(".")) {
            handleDotButton(expression);
        } else if (buttonText.equals("AC")) {
            clearAll();
        } else if (buttonText.equals("C")) {
            clearLast();
        } else {
            handleExpressionInput(buttonText, expression);
        }
    }

    private void initializeButton(int buttonId) {
        MaterialButton button = findViewById(buttonId);
        button.setOnClickListener(this);
    }


    private void clearAll() {
        solutionTv.setText("");
        resultTv.setText("");
    }

    private void clearLast() {
        solutionTv.setText("");
        resultTv.setText("");
    }

    private void handleDotButton(String expression) {
        String lastNum = "";
        for (int i = expression.length() - 1; i >= 0; i--) {
            if (isSolver(expression.charAt(i))) {
                break;
            }
            lastNum += expression.charAt(i);
        }

        if (lastNum.isEmpty()) {
        } else if (lastNum.contains(".")) {
            if (lastNum.charAt(0) == '.') {
                expression = expression.substring(0, expression.length() - 1);
            }
        } else {
            expression += ".";
        }

        solutionTv.setText(expression);
    }

    private void handleExpressionInput(String buttonText, String expression) {
        String lastChar = expression.isEmpty() ? "" : expression.substring(expression.length() - 1);

        if (!expression.isEmpty() && isSolver(lastChar.charAt(0)) && isSolver(buttonText.charAt(0))) {
            expression = expression.substring(0, expression.length() - 1);
        }

        expression += buttonText;
        solutionTv.setText(expression);

        if (!isSolver(buttonText)) {
            String result = evaluateExpression(expression);
            resultTv.setText(result);
        }
    }

    private String evaluateExpression(String expression) {
        ArrayList<String> finalData = tokenizeExpression(expression);

        Stack<String> stackOp = new Stack<>();
        stackOp.push(finalData.get(0));

        for (int i = 1; i < finalData.size(); i++) {
            if (isSolver(finalData.get(i))) {
                handleOperator(stackOp, finalData.get(i), finalData.get(++i));
            } else {
                stackOp.push(finalData.get(i));
            }
        }

        double answer = Double.parseDouble(stackOp.get(0));
        for (int i = 1; i < stackOp.size(); i += 2) {
            handleOperator(stackOp, stackOp.get(i), stackOp.get(i + 1));
        }

        return String.valueOf(answer);
    }

    private void handleOperator(Stack<String> stack, String operator, String operand) {
        double prev = Double.parseDouble(stack.pop());
        double next = Double.parseDouble(operand);

        switch (operator) {
            case "+":
                stack.push(String.valueOf(prev + next));
                break;
            case "-":
                stack.push(String.valueOf(prev - next));
                break;
            case "*":
                stack.push(String.valueOf(prev * next));
                break;
            case "/":
                stack.push(String.valueOf(prev / next));
                break;
        }
    }

    private ArrayList<String> tokenizeExpression(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                currentToken.append(c);
            } else {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private boolean isSolver(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isSolver(String s){
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }


}
