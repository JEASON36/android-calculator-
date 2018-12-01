package com.example.peipeng.test;
import android.app.Activity;
//import android.content.DialogInterface;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    Button bt_0,bt_1,bt_2,bt_3,bt_4,bt_5,bt_6,bt_7,bt_8,bt_9,bt_pt;
    Button bt_mul,bt_div,bt_add,bt_sub;//* %
    Button bt_clr,bt_del,bt_eq;//
    EditText et_input;
    boolean clr_flag;    //判断et中是否清空

    private String p1,p2,p3;//运算符左右的数字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化对象
        setContentView(R.layout.activity_main);
        bt_0= (Button) findViewById(R.id.bt_0);
        bt_1= (Button) findViewById(R.id.bt_1);
        bt_2= (Button) findViewById(R.id.bt_2);
        bt_3= (Button) findViewById(R.id.bt_3);
        bt_4= (Button) findViewById(R.id.bt_4);
        bt_5= (Button) findViewById(R.id.bt_5);
        bt_6= (Button) findViewById(R.id.bt_6);
        bt_7= (Button) findViewById(R.id.bt_7);
        bt_8= (Button) findViewById(R.id.bt_8);
        bt_9= (Button) findViewById(R.id.bt_9);
        bt_pt= (Button) findViewById(R.id.bt_pt);
        bt_add= (Button) findViewById(R.id.bt_add);
        bt_sub= (Button) findViewById(R.id.bt_sub);
        bt_mul= (Button) findViewById(R.id.bt_mul);
        bt_div= (Button) findViewById(R.id.bt_div);
        bt_clr= (Button) findViewById(R.id.bt_clr);
        bt_del= (Button) findViewById(R.id.bt_del);
        bt_eq= (Button) findViewById(R.id.bt_eq);
        et_input= (EditText) findViewById(R.id.et_input);

        //设置按钮的点击事件
        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_pt.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_sub.setOnClickListener(this);
        bt_mul.setOnClickListener(this);
        bt_div.setOnClickListener(this);
        bt_clr.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_eq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        switch (v.getId()){
            case   R.id.bt_0://  switch的用法是判断case后面的表达式和switch后面的表达式是否相匹配，一旦case匹配,就会顺序执行后面的程序代码,而不管后面的case是否匹配,直到遇见break。
            case   R.id.bt_1:
            case   R.id.bt_2:
            case   R.id.bt_3:
            case   R.id.bt_4:
            case   R.id.bt_5:
            case   R.id.bt_6:
            case   R.id.bt_7:
            case   R.id.bt_8://case没有break就一直运行下来
            case   R.id.bt_9:
            case   R.id.bt_pt:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;//到break就跳出

//            可以直接在这个位置，截取S1 符号下方截取 S2,运算符直接使用equals("×"))来获取
            case R.id.bt_add:
            case R.id.bt_sub:
            case R.id.bt_mul:
            case R.id.bt_div://
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }

                if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")) {
                    str=str.substring(0,str.indexOf(" "));
                }
                p1=et_input.getText().toString();//获取P1
                Toast.makeText(MainActivity.this,"P1="+p1,Toast.LENGTH_SHORT).show();
//                et_input.setText(str+" "+((Button)v).getText()+" ");//使用空格来分割算式
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.bt_clr:
                if(clr_flag)
                    clr_flag=false;
                str="";
                et_input.setText("");
                break;
            case R.id.bt_del: //判断是否为空，然后在进行删除
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.bt_eq:
                get();
                break;
        }
    }


    private void get(){
        String exp=et_input.getText().toString();
        int n = p1.length();
        String op = exp.substring(n,n+1);//运算符
        String p3 = exp.substring(n+1);
        double d1=Double.parseDouble(p1);//第一个数字
        double d2=Double.parseDouble(p3);//第二个数字
        double cnt=0;
        if(exp == null) return;
        if(op=="null") return;
        if(!p1.equals("")&&!p3.equals("")){//p1p2不为空
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0) {
                    Toast.makeText(MainActivity.this,"无法除以零",Toast.LENGTH_SHORT).show();
                    cnt=0;}
                else cnt=d1/d2;
            }
            if(!p1.contains(".")&&!p3.contains(".")&&!op.equals("÷")) {//如果p1 p2 都是int 显示结果也是int
                int res = (int)cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else if(!p1.equals("")&&p3.equals("")){//p2为空
            Toast.makeText(MainActivity.this,"运算符后为空",Toast.LENGTH_SHORT).show();
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!op.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else if(p1.equals("")&&!p3.equals("")){//p1为空 p2不为空
            Toast.makeText(MainActivity.this,"运算符前为空",Toast.LENGTH_SHORT).show();
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!p3.contains(".")) {
                int res = (int) cnt;

                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else {
            et_input.setText("");
        }
    }}


