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
 * Created by stre6 on 2017-01-13.
 */

public class Jc extends AppCompatActivity {
    String id;
    String res;
    ArrayList iid = new ArrayList();

    public String gre() {
        return res;
    }

    public void yy(String cid) {
        id = cid;

        class jc extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                StringBuilder sb = new StringBuilder();
                try {
                    String link = "http://1.224.44.55/wjddnr/joinc.php";
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
                res = ju(sb.toString());
                return res;
            }
        }
        try {
            res = new jc().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    String ju(String j) {
        String cid;
        try {
            iid.add("");
            JSONArray a = new JSONArray(j);
            for (int i = 0; i < a.length(); i++) {
                JSONObject o = a.getJSONObject(i);
                cid = o.getString("id");
                iid.add(cid);
                Log.e("iiiddd", cid);
            }
        } catch (JSONException e) {
        }
        for (int b = 0; b < iid.size(); b++) {
            if (id.equals(iid.get(b).toString().trim())) {
                res = "same";
                break;
            } else {
                res = "ss";
            }
        }
        return res;
    }
}
