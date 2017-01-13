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
 * Created by stre6 on 2017-01-04.
 */

public class GaesipanCheck extends AppCompatActivity {

    String res;
    String num, gsna, naeyoung, nick;
    ArrayList ln = new ArrayList();
    ArrayList lg = new ArrayList();
    ArrayList lna = new ArrayList();
    ArrayList lci = new ArrayList();

    public String getres() {
        return res;
    }

    public void ch(String cnum, String cgsna, String cnaeyoung, String cnick) {
        num = cnum;
        gsna = cgsna;
        naeyoung = cnaeyoung;
        nick = cnick;

        class lc extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                StringBuilder sb = new StringBuilder();
                try {
                    String link = "http://1.224.44.55/wjddnr/gaesipangle.php";
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setDefaultUseCaches(false);
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                res = qq(sb.toString());
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

    String qq(String j) {
        String cnum, cgsna, cnaeyoung, cnick;
        try {
            JSONArray a = new JSONArray(j);
            ln.clear();
            lg.clear();
            lna.clear();
            lci.clear();
            for (int i = 0; i < a.length(); i++) {
                JSONObject o = a.getJSONObject(i);
                cnum = String.valueOf(o.getInt("sonbun"));
                cgsna = o.getString("gsna");
                cnaeyoung = o.getString("naeyoung");
                cnick = o.getString("nick");
                Log.e("cn", cnum);
                Log.e("cg", cgsna);
                Log.e("cnae", cnaeyoung);
                Log.e("cnick", cnick);
                ln.add(cnum);
                lg.add(cgsna);
                lna.add(cnaeyoung);
                lci.add(cnick);
            }

        } catch (JSONException e) {
        }
        return res;
    }
}
