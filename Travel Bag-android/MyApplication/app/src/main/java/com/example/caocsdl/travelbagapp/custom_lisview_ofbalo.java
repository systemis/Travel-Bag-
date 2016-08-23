package com.example.caocsdl.travelbagapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nickpham on 20/06/2016.
 */
public class custom_lisview_ofbalo extends BaseAdapter {
    Context context;int Layout;List<csdlDanhsachcacthutrongbalo>Danhsach;
    public custom_lisview_ofbalo(Context context,int Layout,List<csdlDanhsachcacthutrongbalo> Danhsach)
    {
        this.context = context;
        this.Layout = Layout;
        this.Danhsach = Danhsach;
    }
    private static class ViewHolder
    {
        private ImageView imgHinhanhdodung;
        private TextView txtTendodung;
        private ImageButton imgbtXoadanhsachbalo;
    }
    @Override
    public int getCount() {
        return Danhsach.size();
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
        View viewRop = convertView;
        if(viewRop==null)
        {
            viewRop = inflater.inflate(Layout,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imgHinhanhdodung = (ImageView)viewRop.findViewById(R.id.imgDodungdulichOfbalo);
            viewHolder.txtTendodung = (TextView)viewRop.findViewById(R.id.tendodung);
            viewHolder.imgbtXoadanhsachbalo = (ImageButton)viewRop.findViewById(R.id.btLXoaDodunglsBalo);
            viewRop.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) viewRop.getTag();
        viewHolder.txtTendodung.setText(Danhsach.get(position).getDodac());
        xulyDodungHinhanh(position,viewHolder);
        viewHolder.imgbtXoadanhsachbalo.setOnClickListener(new XulyClick(position));
        return viewRop;
    }

    private void xulyDodungHinhanh(int position, ViewHolder viewHolder)
    {
        if(Danhsach.get(position).getDodac().equals("Sạc dữ phòng và cáp "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.sacduphong);
        }
        if(Danhsach.get(position).getDodac().equals("Thuốc men"))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.thuocmen);
        }
        if(Danhsach.get(position).getDodac().equals("Nước lọc "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.water);
        }
        if(Danhsach.get(position).getDodac().equals("Giấy tờ tuỳ thân "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.giayto);
        }
        if(Danhsach.get(position).getDodac().equals("Điện thoại "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.dienthoaididong);
        }
        if(Danhsach.get(position).getDodac().equals("Máy nghe nhạc "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.maynghenhac);
        }
        if(Danhsach.get(position).getDodac().equals("Sách kỹ năng sống "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.sachkynangsong);
        }
        if(Danhsach.get(position).getDodac().equals("Máy ảnh(Nếu có ) "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.mayanh);
        }
        if(Danhsach.get(position).getDodac().equals("Áo gió "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.aogio);
        }
        if(Danhsach.get(position).getDodac().equals("Áo ấm "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.aoam);
        }
        if(Danhsach.get(position).getDodac().equals("Quần dài "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.quandai);
        }
        if(Danhsach.get(position).getDodac().equals("Đèn pin "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.denpin_2);
        }
        if(Danhsach.get(position).getDodac().equals("Dây thừng "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.rope);
        }
        if(Danhsach.get(position).getDodac().equals("Kính râm "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.kinhram);
        }

        if(Danhsach.get(position).getDodac().equals("Máy tính "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.laptop);
        }
        if(Danhsach.get(position).getDodac().equals("Lều "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.leu);
        }
        if(Danhsach.get(position).getDodac().equals("Áo tay ngắn "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.aotayngan);
        }
        if(Danhsach.get(position).getDodac().equals("Áo thun mỏng "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.aothunmong);
        }
        if(Danhsach.get(position).getDodac().equals("Quần đùi "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.quandui);
        }
        if(Danhsach.get(position).getDodac().equals("Bình nước giữ nhiệt "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.binhgiunhiet);
        }
        if(Danhsach.get(position).getDodac().equals("Thực phẩm dự trữ "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.thucphamdutru);
        }
        if(Danhsach.get(position).getDodac().equals("Giày thể thao "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.giaythethao);
        }
        if(Danhsach.get(position).getDodac().equals("Dép lê(Thoải mái khi du lịch ) "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.deple);
        }
        if(Danhsach.get(position).getDodac().equals("Quẹt diêm "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.quetdiem);
        }
        if(Danhsach.get(position).getDodac().equals("Aó phao cứu hộ "))
        {
            viewHolder.imgHinhanhdodung.setImageResource(R.mipmap.aophao);
        }
    }

    public class XulyClick implements View.OnClickListener {
        int position;
        public  XulyClick(int position)
        {
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            Danhsach.remove(position);
            notifyDataSetChanged();
        }
    }
}
