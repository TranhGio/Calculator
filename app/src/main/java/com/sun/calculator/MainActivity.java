package com.sun.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewExpression, textViewResult;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewExpression = findViewById(R.id.textViewExpression);
        textViewResult = findViewById(R.id.textViewResult);
        textViewExpression.setText("");
        textViewResult.setText("");
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                textViewResult.setText("");
                textViewExpression.setText("");
                return true;
            }
        });

    }
    public void calculateExpression(){
        if(textViewExpression.getText().toString().isEmpty() || textViewExpression.getText().toString().equals("-")){
            return;
        }
        String expression = textViewExpression.getText().toString();
        char operator = '\0';
        String a = "",b = "";
        int lastIndexOfSubtract = expression.lastIndexOf("-");
        for(int i=1;i<expression.length(); i++){
            if (expression.charAt(i) == 'x' ||expression.charAt(i) == '+' || lastIndexOfSubtract == i || expression.charAt(i) == '/'){
                operator = expression.charAt(i);
                a = expression.substring(0,i);
                b = expression.substring(i+1);
                break;
            }
        }

        if(operator == '\0' || a.equals("") || b.equals("")) return;
        float result = 0.0f;
        switch (operator){
            case '+':
                result = Float.valueOf(a) + Float.valueOf(b);
                break;
            case '-':
                result = Float.valueOf(a) - Float.valueOf(b);
                break;
            case 'x':
                result = Float.valueOf(a) * Float.valueOf(b);
                break;
            case '/':
                if(Float.valueOf(b) == 0f){
                    Toast.makeText(this, "Can't be divided by 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = Float.valueOf(a) / Float.valueOf(b);
                break;
        }
        textViewResult.setText(String.valueOf(result));
    }

    public void addCharToTextViewExpression(String s) {
        if(!textViewExpression.getText().toString().isEmpty()){
            if(s.equals(".") || s.equals("x") || s.equals("/") || s.equals("-") || s.equals("+")){
                int length = textViewExpression.getText().toString().length();

                char lastChar = textViewExpression.getText().charAt(length-1);
                if(lastChar == '.' || lastChar == 'x' || lastChar == '/' || lastChar == '+' || lastChar == '-'){
                    return;
                }
            }
        }
        textViewExpression.setText(textViewExpression.getText().toString().concat(s));
    }

    public void removeLastCharToTextViewExpression(){
        if(!textViewExpression.getText().toString().isEmpty()) {
            textViewExpression.setText(textViewExpression.getText().toString().substring(0,textViewExpression.getText().toString().length()-1));
        }
    }

    public void setTextViewExpressionByTextViewResult(){
        textViewExpression.setText(textViewResult.getText().toString());
        textViewResult.setText("");
    }



    public void onClickAnyButton(View view) {
        switch (view.getId()) {
            case R.id.button0:
                addCharToTextViewExpression("0");
                calculateExpression();
                break;
            case R.id.button1:
                addCharToTextViewExpression("1");
                calculateExpression();
                break;
            case R.id.button2:
                addCharToTextViewExpression("2");
                calculateExpression();
                break;
            case R.id.button3:
                addCharToTextViewExpression("3");
                calculateExpression();
                break;
            case R.id.button4:
                addCharToTextViewExpression("4");
                calculateExpression();
                break;
            case R.id.button5:
                addCharToTextViewExpression("5");
                calculateExpression();
                break;
            case R.id.button6:
                addCharToTextViewExpression("6");
                calculateExpression();
                break;
            case R.id.button7:
                addCharToTextViewExpression("7");
                calculateExpression();
                break;
            case R.id.button8:
                addCharToTextViewExpression("8");
                calculateExpression();
                break;
            case R.id.button9:
                addCharToTextViewExpression("9");
                calculateExpression();
                break;
            case R.id.buttonAdd:
                if(!textViewResult.getText().toString().isEmpty()){
                    setTextViewExpressionByTextViewResult();
                }
                addCharToTextViewExpression("+");
                break;
            case R.id.buttonSubtract:
                if(!textViewResult.getText().toString().isEmpty()){
                    setTextViewExpressionByTextViewResult();
                }
                addCharToTextViewExpression("-");
                break;
            case R.id.buttonMulti:
                if(!textViewResult.getText().toString().isEmpty()){
                    setTextViewExpressionByTextViewResult();
                }
                addCharToTextViewExpression("x");
                break;
            case R.id.buttonDiv:
                if(!textViewResult.getText().toString().isEmpty()){
                    setTextViewExpressionByTextViewResult();
                }
                addCharToTextViewExpression("/");
                break;
            case R.id.buttonPoint:
                addCharToTextViewExpression(".");
                break;
            case R.id.buttonDelete:
                removeLastCharToTextViewExpression();
                break;
            case R.id.buttonEqual:
                calculateExpression();
                setTextViewExpressionByTextViewResult();
                break;
        }
    }
}
