package com.kaka.gaesipan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by stre6 on 2017-01-02.
 */

public class Join extends AppCompatActivity {
    Button signbt, back;
    EditText silo, sipw, nick, email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        signbt = (Button) findViewById(R.id.signbt);
        back = (Button) findViewById(R.id.back);
        silo = (EditText) findViewById(R.id.silo);
        sipw = (EditText) findViewById(R.id.sipw);
        nick = (EditText) findViewById(R.id.nick);
        email = (EditText) findViewById(R.id.email);

        signbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l = silo.getText().toString();
                String p = sipw.getText().toString();
                String n = nick.getText().toString();
                String e = email.getText().toString();
                String res = "";
                Jc jc = new Jc();
                jc.yy(l);
                res = jc.gre();
                if (res.equals("same")) {
                    Toast.makeText(Join.this, "ID중복", Toast.LENGTH_SHORT).show();
                } else if (res.equals("ss")) {
                    Toast.makeText(Join.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    Db(l, p, n, e);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Db(String l, String p, String n, String e) {
        class ID extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    String l = params[0];
                    String p = params[1];
                    String n = params[2];
                    String e = params[3];
                    String link = "http://1.224.44.55/wjddnr/gaesipan.php";
                    String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(l, "UTF-8");
                    data += "&" + URLEncoder.encode("pw", "UTF-8") + "=" + URLEncoder.encode(p, "UTF-8");
                    data += "&" + URLEncoder.encode("nick", "UTF-8") + "=" + URLEncoder.encode(n, "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(e, "UTF-8");
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDefaultUseCaches(false);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(data);
                    wr.flush();
                    wr.close();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        ID task = new ID();
        task.execute(l, p, n, e);
    }
}