package com.kaka.gaesipan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button sign;
    EditText id,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign=(Button)findViewById(R.id.sign);
        id=(EditText)findViewById(R.id.id);
        pw=(EditText)findViewById(R.id.pw);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Join.class);
                startActivity(i);
            }
        });
    }
    public void login(View v){
        String Id=id.getText().toString();
        String Pw=pw.getText().toString();
        String pp="";
        String res = "";
        Lc lc = new Lc();
        lc.aa(Id,Pw,pp);
        res=lc.getres();
        Log.e("pp",pp);
        if (res.equals("Su")){
            id.setText("");
            pw.setText("");
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this,Gaesipan.class);
            startActivity(i);
        }else if (TextUtils.isEmpty(Id)||TextUtils.isEmpty(Pw)) {
            Toast.makeText(this, "ID또는 PW를 입력하십시오", Toast.LENGTH_SHORT).show();
        }else if (res.equals("fail")){
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
