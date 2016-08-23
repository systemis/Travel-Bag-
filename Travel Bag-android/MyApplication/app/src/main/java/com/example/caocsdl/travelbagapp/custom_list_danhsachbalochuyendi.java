package com.example.caocsdl.travelbagapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nickpham on 14/06/2016.
 */
public class custom_list_danhsachbalochuyendi extends BaseAdapter {
    Context context;int Layout;String[]dulieucongcu;
    public custom_list_danhsachbalochuyendi(Context context,int Layout,String[] dulieucongcu)
    {
        this.context = context;
        this.Layout = Layout;
        this.dulieucongcu = dulieucongcu;
    }
    @Override
    public int getCount() {
        return dulieucongcu.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(Layout,parent,false);
        ImageView imgHinhanhdungcu = (ImageView)convertView.findViewById(R.id.imgDodungdulichOfbalo);
        TextView txtTendungcu = (TextView)convertView.findViewById(R.id.tendodung);
        Button btXoaL = (Button)convertView.findViewById(R.id.Dthoat);
        return convertView;
    }
}
