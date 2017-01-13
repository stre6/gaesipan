package com.kaka.gaesipan;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by stre6 on 2017-01-03.
 */

public class Sangcattle extends AppCompatActivity {
    EditText g1, g2;
    Button b1, b2;
    TextView ni;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sangcattle);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        g1 = (EditText) findViewById(R.id.g1);
        g2 = (EditText) findViewById(R.id.g2);
        ni = (TextView)findViewById(R.id.nik);
        setTitle("게시물 생성");

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nik="";
                String gsna = g1.getText().toString();
                String nae = g2.getText().toString();
                id(gsna, nae, nik);
                Toast.makeText(Sangcattle.this, "생성완료", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(Sangcattle.this,Gaesipan.class);
                startActivity(a);
                finish();
            }
        });
    }


    private void id(String gsna, String nae, String nik) {
        class sd extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    String gsna = params[0];
                    String nae = params[1];
                    String nik = params[2];

                    String link = "http://1.224.44.55/wjddnr/gle.php";
                    String data = URLEncoder.encode("gsna", "UTF-8") + "=" + URLEncoder.encode(gsna, "UTF-8");
                    data += "&" + URLEncoder.encode("naeyoung", "UTF-8") + "=" + URLEncoder.encode(nae, "UTF-8");
                    data += "&" + URLEncoder.encode("nick", "UTF-8") + "=" + URLEncoder.encode(nik, "UTF-8");
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
        sd task = new sd();
        task.execute(gsna, nae, nik);
    }
}
