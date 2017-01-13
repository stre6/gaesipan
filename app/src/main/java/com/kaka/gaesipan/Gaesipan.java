package com.kaka.gaesipan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by stre6 on 2017-01-02.
 */

public class Gaesipan extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    String son;
    String ja;
    String nae;
    String nick;
    Context context;
    SwipeRefreshLayout sf;
    ScrollView sc;
    ListView listView;
    Button re;
    GaesipanCheck gc = new GaesipanCheck();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaesipan);
        sf = (SwipeRefreshLayout) findViewById(R.id.sl);
        listView = (ListView) findViewById(R.id.lv);
        sf.setOnRefreshListener(this);
        sf.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        setTitle("인평게시판");
        final ListviewAdapter adapter = new ListviewAdapter();
        sc=(ScrollView)findViewById(R.id.sc);
        listView.setAdapter(adapter);
        gc.ch(son, ja, nae,nick);
        for (int i = 0; i < gc.ln.size(); i++) {
            son = (String) gc.ln.get(i);
            ja = (String) gc.lg.get(i);
            nae = (String) gc.lna.get(i);
            nick = (String) gc.lci.get(i);
            adapter.addItem(son, ja, nae,nick);
        }
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListviewItem item = (ListviewItem) parent.getItemAtPosition(position);
                String son = item.getNumber();
                String title = item.getTitle();
                String nae = item.getNae();
                String n = item.getNick();
                Intent a = new Intent(getApplicationContext(), Gaesipanintent.class);
                a.putExtra("son", "번호:"+son);
                a.putExtra("title", "제목:"+title);
                a.putExtra("nae", nae);
                a.putExtra("nick", n);
                startActivity(a);
            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                sc.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ab) {
            Intent i = new Intent(Gaesipan.this, Sangcattle.class);
            startActivity(i);
            finish();
            return true;
        } else if (id == R.id.restart){
            restart(Gaesipan.this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void restart(Activity a){
        Intent k = new Intent();
        k.setClass(a,a.getClass());
        a.finish();
        a.startActivity(k);
    }
    @Override
    public void onRefresh() {
        restart(Gaesipan.this);
        sf.setRefreshing(false);
    }

}
