package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv_result;
    private Button btn_cancel;
    private Button btn_divide;
    private Button btn_multiply;
    private Button btn_clean;
    private Button btn_seven;
    private Button btn_eight;
    private Button btn_nine;
    private Button btn_plus;
    private Button btn_four;
    private Button btn_five;
    private Button btn_six;
    private Button btn_minus;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_reciprocal;
    private ImageButton ib_sqrt;
    private Button btn_zero;
    private Button btn_dot;
    private Button btn_equal;
    MyListener listener;
    //第一个操作数
    private String firstNum="";
    //运算符
    private String operator="";
    //第二个操作数
    private String SecondNum="";
    //当前的计算结果
    private  String result="";
    //显示的文本内容
    private  String showText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener=new MyListener(this);
        tv_result = findViewById(R.id.tv_result);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(listener);
        findViewById(R.id.btn_divide).setOnClickListener(listener);
        findViewById(R.id.btn_multiply).setOnClickListener(listener);
        findViewById(R.id.btn_clean).setOnClickListener(listener);
        findViewById(R.id.btn_seven).setOnClickListener(listener);
        findViewById(R.id.btn_eight).setOnClickListener(listener);
        findViewById(R.id.btn_nine).setOnClickListener(listener);
        findViewById(R.id.btn_plus).setOnClickListener(listener);
        findViewById(R.id.btn_four).setOnClickListener(listener);
        findViewById(R.id.btn_five).setOnClickListener(listener);
        findViewById(R.id.btn_six).setOnClickListener(listener);
        findViewById(R.id.btn_minus).setOnClickListener(listener);
        findViewById(R.id.btn_one).setOnClickListener(listener);
        findViewById(R.id.btn_two).setOnClickListener(listener);
        findViewById(R.id.btn_three).setOnClickListener(listener);
        findViewById(R.id.ib_sqrt).setOnClickListener(listener);
        findViewById(R.id.btn_reciprocal).setOnClickListener(listener);
        findViewById(R.id.btn_zero).setOnClickListener(listener);
        findViewById(R.id.btn_dot).setOnClickListener(listener);
        findViewById(R.id.btn_equal).setOnClickListener(listener);
        findViewById(R.id.btn_equal).setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if(getShowText().equals("20021216")) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, EasterEggActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    public TextView getTv_result() {
        return tv_result;
    }

    public Button getBtn_cancel() {
        return btn_cancel;
    }

    public Button getBtn_divide() {
        return btn_divide;
    }

    public Button getBtn_multiply() {
        return btn_multiply;
    }

    public Button getBtn_clean() {
        return btn_clean;
    }

    public Button getBtn_seven() {
        return btn_seven;
    }

    public Button getBtn_eight() {
        return btn_eight;
    }

    public Button getBtn_nine() {
        return btn_nine;
    }

    public Button getBtn_plus() {
        return btn_plus;
    }

    public Button getBtn_four() {
        return btn_four;
    }

    public Button getBtn_five() {
        return btn_five;
    }

    public Button getBtn_six() {
        return btn_six;
    }

    public Button getBtn_minus() {
        return btn_minus;
    }

    public Button getBtn_one() {
        return btn_one;
    }

    public Button getBtn_two() {
        return btn_two;
    }

    public Button getBtn_three() {
        return btn_three;
    }

    public Button getBtn_reciprocal() {
        return btn_reciprocal;
    }

    public ImageButton getIb_sqrt() {
        return ib_sqrt;
    }

    public Button getBtn_zero() {
        return btn_zero;
    }

    public Button getBtn_dot() {
        return btn_dot;
    }

    public Button getBtn_equal() {
        return btn_equal;
    }

    public MyListener getListener() {
        return listener;
    }

    public String getFirstNum() {
        return firstNum;
    }

    public String getSecondNum() {
        return SecondNum;
    }

    public String getResult() {
        return result;
    }

    public String getShowText() {
        return showText;
    }

    public String getOperator() {
        return operator;
    }

    public void setFirstNum(String firstNum) {
        this.firstNum = firstNum;
    }

    public void setSecondNum(String secondNum) {
        SecondNum = secondNum;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setResult(String result) {
        this.result = result;
    }

}