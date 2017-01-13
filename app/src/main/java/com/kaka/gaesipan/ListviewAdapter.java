package com.kaka.gaesipan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by stre6 on 2017-01-03.
 */

public class ListviewAdapter extends BaseAdapter{

    private ArrayList<ListviewItem> listviewItems = new ArrayList<ListviewItem>();
    public ListviewAdapter(){

    }

    @Override
    public int getCount() {
        return listviewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listviewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item,parent,false);
        }
        TextView number = (TextView)convertView.findViewById(R.id.tv1);
        TextView title = (TextView)convertView.findViewById(R.id.tv2);
        TextView nae = (TextView)convertView.findViewById(R.id.tv6);
        TextView nick = (TextView)convertView.findViewById(R.id.tv8);
        ListviewItem listviewItem = listviewItems.get(position);

        number.setText(listviewItem.getNumber());
        title.setText("제목:"+listviewItem.getTitle());
        nae.setText(listviewItem.getNae());
        nick.setText(listviewItem.getNick());
        return convertView;
    }
    public void addItem(String number,String title,String nae,String nick){
        ListviewItem item = new ListviewItem();
        item.setNumber(number);
        item.setTitle(title);
        item.setnae(nae);
        item.setNick(nick);
        listviewItems.add(item);
    }
}
