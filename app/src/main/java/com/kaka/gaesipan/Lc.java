package com.kaka.gaesipan;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by stre6 on 2017-01-03.
 */

public class Lc extends AppCompatActivity {
    String res;
    String id;
    String pw;
    String nick;
    String m;
    ArrayList Id = new ArrayList();
    ArrayList Pw = new ArrayList();
    ArrayList Nick = new ArrayList();
    Sangcattle s = new Sangcattle();

    public String getres() {
        return res;
    }

    public void aa(String cid, String cpw, String cnick) {
        id = cid;
        pw = cpw;
        nick = cnick;

        class lc extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                StringBuilder sb = new StringBuilder();
                try {
                    String link = "http://1.224.44.55/wjddnr/gaesipanlo.php";
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDefaultUseCaches(false);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                res = ja(sb.toString());
                return res;
            }
        }
        try {
            res = new lc().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    String ja(String j) {
        String cid, cpw, cnick;
        try {
            JSONArray a = new JSONArray(j);
            for (int i = 0; i < a.length(); i++) {
                JSONObject o = a.getJSONObject(i);
                cid = o.getString("id");
                cpw = o.getString("pw");
                cnick = o.getString("nick");
                Id.add(cid);
                Pw.add(cpw);
                Nick.add(cnick);
                Log.e("Nick", String.valueOf(Nick));
            }
        } catch (JSONException e) {
        }
        for (int b = 0; b < Id.size(); b++) {
            if (id.equals(Id.get(b).toString().trim()) && pw.equals(Pw.get(b).toString().trim())) {
                res = "Su";
                m = Nick.get(b).toString();
                Log.e("mmmm",m);
                break;
            } else {
                res = "fail";
            }
        }
        return res;
    }
}
