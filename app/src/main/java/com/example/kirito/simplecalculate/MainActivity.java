package com.example.kirito.simplecalculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_clear,btn_del,btn_div,btn_mul,
            btn_four,btn_five,btn_six,btn_plus,
            btn_seven,btn_eight,btn_nine,btn_sub,
            btn_one,btn_two,btn_three,btn_equ,
            btn_zero,btn_point;

    private TextView tv_in,tv_out;
    private String content;
    private boolean isDup = false;
    private boolean isSigh = false;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();
    }

    private void init(){
        tv_in = (TextView) findViewById(R.id.tv_input);
        tv_out = (TextView) findViewById(R.id.tv_output);

        btn_clear = (Button) findViewById(R.id.btn_cle);
        btn_clear.setOnClickListener(this);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_del.setOnClickListener(this);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_div.setOnClickListener(this);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_mul.setOnClickListener(this);

        btn_four = (Button) findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this);
        btn_five = (Button) findViewById(R.id.btn_five);
        btn_five.setOnClickListener(this);
        btn_six = (Button) findViewById(R.id.btn_six);
        btn_six.setOnClickListener(this);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(this);

        btn_seven = (Button) findViewById(R.id.btn_seven);
        btn_seven.setOnClickListener(this);
        btn_eight = (Button) findViewById(R.id.btn_eight);
        btn_eight.setOnClickListener(this);
        btn_nine = (Button) findViewById(R.id.btn_nine);
        btn_nine.setOnClickListener(this);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(this);

        btn_one = (Button) findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        btn_equ = (Button) findViewById(R.id.btn_equ);
        btn_equ.setOnClickListener(this);

        btn_zero = (Button) findViewById(R.id.btn_zero);
        btn_zero.setOnClickListener(this);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_point.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (content == null){
            content = "";
        }

        switch (v.getId()){
            case R.id.btn_cle:
                clear();
                break;
            case R.id.btn_del:
                tv_in.setText(deleOneWord());
                break;

            case R.id.btn_plus:
                isSigh = true;
                input("+");
                break;
            case R.id.btn_sub:
                isSigh = true;
                input("-");
                break;
            case R.id.btn_div:
                isSigh = true;
                input("÷");
                break;
            case R.id.btn_mul:
                isSigh = true;
                input("X");
                break;
            case R.id.btn_point:
                isSigh = false;
                input(".");
                break;
            case R.id.btn_equ:
                calculate();
                break;

            case R.id.btn_zero:
                isSigh = false;
                input("0");
                break;
            case R.id.btn_one:
                isSigh = false;
                input("1");
                break;
            case R.id.btn_two:
                isSigh = false;
                input("2");
                break;
            case R.id.btn_three:
                isSigh = false;
                input("3");
                break;
            case R.id.btn_four:
                isSigh = false;
                input("4");
                break;
            case R.id.btn_five:
                isSigh = false;
                input("5");
                break;
            case R.id.btn_six:
                isSigh = false;
                input("6");
                break;
            case R.id.btn_seven:
                isSigh = false;
                input("7");
                break;
            case R.id.btn_eight:
                isSigh = false;
                input("8");
                break;
            case R.id.btn_nine:
                isSigh = false;
                input("9");
                break;
        }
    }

    private void calculate() {
        float result = 0;
        String first = content.substring(0,content.indexOf(" "));

        String array[] = null;
        String sigh = null;
        array = new String[content.length()];
        for (int i = 0; i < content.length(); i++) {
            array[i] = String.valueOf(content.charAt(i));
            if (array[i].equals("+") || array[i].equals("-") ||
                    array[i].equals("X") || array[i].equals("÷")){ //array[i] == "+"无效
                sigh = array[i];
            }
        }
        String second = content.substring(content.indexOf(sigh) + 2,content.length());

        float fir = Float.valueOf(first);
        float sec = Float.valueOf(second);

        if (sigh.equals("+")){
            result = fir + sec;
        }else if (sigh.equals("-")){
            result = fir - sec;
        }else if (sigh.equals("X")){
            result = fir * sec;
        }else if (sigh.equals("÷") && sec != 0){
            result = fir / sec;
        }
        tv_out.setText(result + "");
    }

    private void input(String msg){
        if (isSigh){
            if (!avoidDupSigh() && !isDup) {
                content += " "+ msg +" ";
                tv_in.setText(content);
            }
        }else if (!isSigh){
            content += msg;
            tv_in.setText(content);
        }
    }

    private void clear(){
        tv_in.setText("");
        tv_out.setText("");
        content = "";
        isDup = false;
    }

    //运算符合只能输入一次，防止重复输入
    private boolean avoidDupSigh(){
        isDup = false;
        String array[] = null;
        array = new String[content.length()];
        for (int i = 0; i < content.length(); i++) {
            array[i] = String.valueOf(content.charAt(i));
            if (array[i].equals("+") || array[i].equals("-") ||
                    array[i].equals("X") || array[i].equals("÷")){ //array[i] == "+"无效
                isDup = true;
            }
        }
        return isDup;
    }

    //删除一个输入内容的最后一个字符
    private String deleOneWord(){
        String array[] = null;
        if (content != null && content != ""){

            array = new String[content.length()];
            for (int i = 0; i < content.length() - 1; i++) {
                array[i] = String.valueOf(content.charAt(i));
            }
            StringBuilder sb = new StringBuilder();
            for (String ele : array){
                if (ele == null){
                    continue;
                }
                sb.append(ele);
            }
            content = sb.toString();
            return  content;
        }
        return new String("");
    }
}
