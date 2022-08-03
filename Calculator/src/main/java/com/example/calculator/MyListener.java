package com.example.calculator;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class MyListener implements View.OnClickListener {
    MainActivity main;
    public MyListener(MainActivity mainActivity) {
        main=mainActivity;
    }

    @Override
    public void onClick(View v) {
        String inputText;
        if(v.getId() == R.id.ib_sqrt) {
            inputText ="√";
        }
        else {
            inputText=((TextView)v).getText().toString();
        }
        switch(v.getId()) {
            //点击取消按钮
            case R.id.btn_cancel: {
                if (main.getResult().length()>0 && main.getOperator().equals("")) {
                    clear();
                }
                //当字符串长度不为0才可以消除
                if(main.getShowText().length()>0) {
                    //判断消除的是否是运算符
                    //没有运算符
                    if (main.getOperator().equals("")) {
                        main.setShowText(main.getShowText().substring(0, main.getShowText().length() - 1));
                        main.setFirstNum(main.getFirstNum().substring(0,main.getFirstNum().length() - 1));
                        refreshText(main.getShowText());
                    } else {
                        //有运算符
                        //第二个数不为空
                        if(!main.getSecondNum().equals("")) {
                            main.setShowText(main.getShowText().substring(0, main.getShowText().length() - 1));
                            main.setSecondNum(main.getSecondNum().substring(0,main.getSecondNum().length() - 1));
                            refreshText(main.getShowText());
                        }else {
                            main.setShowText(main.getShowText().substring(0, main.getShowText().length() - 1));
                            main.setOperator("");
                            refreshText(main.getShowText());
                        }
                    }
                    if(main.getShowText().length()==0) {
                        main.getTv_result().setText("0");
                    }
                }
                break;
            }
            //点击清空
            case R.id.btn_clean: {
                main.getTv_result().setText("0");
                clear();
                main.getBtn_cancel().setEnabled(true);
                main.getBtn_cancel().setTextColor(Color.BLACK);
                break;
            }
            //加减乘除
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(!main.getOperator().equals("")) {
                    String str=main.getShowText().substring(main.getShowText().length()-1);
                    if(!str.equals(inputText) && (str.equals("+") || str.equals("-") || str.equals("×") ||str.equals("÷"))) {
                        main.setOperator(inputText);
                        main.setShowText(main.getShowText().substring(0, main.getShowText().length() - 1));
                        refreshText(main.getShowText()+inputText);
                    }
                    break;
                }
                main.setOperator(inputText);
                refreshText(main.getShowText()+inputText);
                break;
            //点击等于
            case R.id.btn_equal: {
                //加减乘除四则运算
                if(main.getSecondNum().equals(""))
                    break;
                BigDecimal calculate_result = calculateFour();
                if(calculate_result.compareTo(BigDecimal.ZERO)==0) {
                    main.getBtn_cancel().setEnabled(false);
                    main.getBtn_cancel().setTextColor(Color.GRAY);
                    main.setShowText("error");
                    refreshText(main.getShowText());
                    break;
                }
                refreshOperate(calculate_result.toString());
                refreshText(main.getShowText()+"="+main.getResult());
                break;
            }
            //开方
            case R.id.ib_sqrt: {
                if(main.getFirstNum().length()==0) {
                    break;
                }
                double sqrt_result =Math.sqrt(Double.parseDouble(main.getFirstNum()));
                refreshOperate(String.valueOf(sqrt_result));
                refreshText(main.getShowText()+"^(-1)="+main.getResult());
                break;
            }
            //倒数
            case R.id.btn_reciprocal: {
                if(main.getFirstNum().length()==0) {
                    break;
                }
                double reciprocal_result= 1.0/ Double.parseDouble(main.getFirstNum());
                refreshOperate(String.valueOf(reciprocal_result));
                refreshText(main.getShowText()+"√="+main.getResult());
                break;
            }
            //点
            case R.id.btn_dot: {
                boolean state;
                //无运算符，则继续拼接第一个操作数
                if (main.getOperator().equals("")) {
                    //一个数只能有一个小数点
                    if(main.getFirstNum().contains(inputText)) {
                        break;
                    }
                    //判断原字符串是否为空串
                    if (main.getFirstNum().equals("")) {
                        main.setFirstNum(0 + inputText);
                        refreshText(0+inputText);
                    } else {
                        main.setFirstNum(main.getFirstNum() + inputText);
                        refreshText(main.getShowText()+inputText);
                    }
                } else {
                    //有运算符，则继续拼接第二个操作数
                    //一个数只能有一个小数点
                    if(main.getSecondNum().contains(inputText)) {
                        break;
                    }
                    if(main.getSecondNum().equals("")) {
                        if(main.getSecondNum().contains(inputText)) {
                            break;
                        }
                        //判断原字符串是否为空串
                        if(main.getSecondNum().equals("")) {
                            main.setSecondNum(0+inputText);
                            refreshText(main.getShowText()+0+inputText);
                        }else {
                            main.setSecondNum(main.getSecondNum() + inputText);
                            refreshText(main.getShowText()+inputText);
                        }
                    }else {
                        main.setSecondNum(main.getSecondNum() + inputText);
                        refreshText(main.getShowText()+inputText);
                    }
                }
                break;
            }
            default: {
                //上一次的运算结果已经出来了
                if (main.getResult().length()>0 && main.getOperator().equals("")) {
                    clear();
                }
                //无运算符，则继续拼接第一个操作数
                if (main.getOperator().equals("")) {
                    main.setFirstNum(main.getFirstNum()+inputText);
                }else {
                    //有运算符，则继续拼接第二个操作数
                    main.setSecondNum(main.getSecondNum()+inputText);
                }
                //整数不需要前面的零
                if(main.getShowText().equals("0") && !inputText.equals(".")) {
                    refreshText(inputText);
                }
                else {
                    refreshText(main.getShowText()+inputText);
                }
                break;
            }
        }
    }

    private BigDecimal calculateFour() {
        BigDecimal num1;
        BigDecimal num2;
        BigDecimal error = new BigDecimal(BigInteger.ZERO);
        try{
            num1=new BigDecimal(main.getFirstNum());
            num2=new BigDecimal(main.getSecondNum());
        }catch(NumberFormatException e) {
            return error;
        }
        switch (main.getOperator()) {
            case "+":
                return num1.add(num2);
            case "−":
                return num1.subtract(num2);
            case "×":
                return num1.multiply(num2);
            case "÷":
                try {
                    return num1.divide(num2);
                } catch (ArithmeticException e) {
                    try {
                        return num1.divide(num2, 16, BigDecimal.ROUND_HALF_UP);
                    } catch (ArithmeticException e0) {
                        break;
                    }
                }
        }
        return error;
    }

    //清空并初始化
    private void clear() {
        main.setShowText("");
        refreshOperate("");
        main.getTv_result().setText("0");
    }

    //刷新运算结果
    private void refreshOperate(String new_result) {
        main.setResult(new_result);
        main.setFirstNum(main.getResult());
        main.setSecondNum("");
        main.setOperator("");
    }

    //刷新文本显示
    private void refreshText(String text) {
        main.setShowText(text);
        main.getTv_result().setText(main.getShowText());
    }
}
