package com.kaka.gaesipan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by stre6 on 2017-01-05.
 */

public class Gaesipanintent extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaesipanintent);
        TextView son = (TextView)findViewById(R.id.tv3);
        TextView title = (TextView)findViewById(R.id.tv4);
        TextView nae = (TextView)findViewById(R.id.tv5);
        TextView nick = (TextView)findViewById(R.id.tv7);
        Intent a = getIntent();
        son.setText(a.getStringExtra("son"));
        title.setText(a.getStringExtra("title"));
        nae.setText(a.getStringExtra("nae"));
        nick.setText(a.getStringExtra("nick"));
    }
}
