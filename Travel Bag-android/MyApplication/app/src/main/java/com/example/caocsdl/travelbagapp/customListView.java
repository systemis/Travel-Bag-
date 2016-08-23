package com.example.caocsdl.travelbagapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nickpham on 13/06/2016.
 */
public class customListView extends BaseAdapter {
    Context context;int Layout;List<csdl>danhsach;
    public customListView(Context context,int Layout, List<csdl> danhsach)
    {
        this.context = context;
        this.Layout = Layout;
        this.danhsach = danhsach;
    }
    public static class ViewHolder
    {
        TextView txtTieude;
        TextView txtThoiGianBD;
        ImageView imgGT;
    }
    @Override
    public int getCount() {
        return danhsach.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRop = convertView;
        if(viewRop==null)
        {
            viewRop = inflater.inflate(Layout,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtTieude = (TextView)viewRop.findViewById(R.id.txtLTieude);
            viewHolder.txtThoiGianBD = (TextView)viewRop.findViewById(R.id.txtLThoigianBD);
            viewHolder.imgGT =  (ImageView)viewRop.findViewById(R.id.anhtieude);
            viewRop.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) viewRop.getTag();
        holder.txtTieude.setText(danhsach.get(position).getTieude());
        holder.txtThoiGianBD.setText(danhsach.get(position).getNgayBatdau()+" " + danhsach.get(position).getThoiGianbatdau());
        holder.imgGT.setImageResource(R.mipmap.mountainview);
        Log.d("Dia hinh theo vi tri",danhsach.get(position).getDiahinh());
        if(danhsach.get(position).getDiahinh().equals("Sông,Suối"))
        {
            holder.imgGT.setImageResource(R.mipmap.song);
        }if(danhsach.get(position).getDiahinh().equals("Núi "))
        {
            holder.imgGT.setImageResource(R.mipmap.mountainview);
        }if(danhsach.get(position).getDiahinh().equals("Khu nghỉ dưỡng ")){
            holder.imgGT.setImageResource(R.mipmap.resortview);
        }
        if(danhsach.get(position).getDiahinh().equals("Biển")) {
            holder.imgGT.setImageResource(R.mipmap.bien);
        }
        return viewRop;
    }
}
