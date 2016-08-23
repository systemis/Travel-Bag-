package com.example.caocsdl.travelbagapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by nickpham on 25/06/2016.
 */
public class fragmentdulieuchuyendichoi extends Fragment {

    Context context;
    int position;
    public fragmentdulieuchuyendichoi(Context context,int position)
    {
        this.context = context;
        this.position = position;
    }
    private EditText edtDiadiemchuyendichoi,edtDiahinhchuyendichoi,edtKhihauchuyendichoi
            ,edtThoigianBDchuyendichoi,edtGioihanchuyendichoi,edtThoigianchuyendichoi;
    custom_Chuyendi Chuyendi = new custom_Chuyendi(context);
    List<csdl>Danhsachdulieu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRop = inflater.inflate(R.layout.fragment_manhinh_thongtin_chuyendulich,container,false);
        edtDiadiemchuyendichoi = (EditText)viewRop.findViewById(R.id.hienthidiadiemdulich);
        edtDiahinhchuyendichoi = (EditText)viewRop.findViewById(R.id.hienthidiahinhchuyendulich);
        edtKhihauchuyendichoi = (EditText)viewRop.findViewById(R.id.hienthikhihaunoidulich);
        edtGioihanchuyendichoi = (EditText)viewRop.findViewById(R.id.hienthiGioihanchuyendi);
        edtThoigianBDchuyendichoi = (EditText)viewRop.findViewById(R.id.hienthithoigianbatdauchuyendi);
        edtThoigianchuyendichoi = (EditText)viewRop.findViewById(R.id.hienthithoigianchuyendichoi);

        Toast.makeText(context,String.valueOf(position), Toast.LENGTH_SHORT).show();
        Danhsachdulieu = new custom_Chuyendi(context).Danhsachchuyendichoi();
        Toast.makeText(context,Danhsachdulieu.get(position).getThoiGianbatdau(), Toast.LENGTH_SHORT).show();

        edtDiadiemchuyendichoi.setText(Danhsachdulieu.get(position).getNoidi());
        edtDiahinhchuyendichoi.setText(Danhsachdulieu.get(position).getDiahinh());
        edtKhihauchuyendichoi.setText(Danhsachdulieu.get(position).getKhihau());
        edtGioihanchuyendichoi.setText(Danhsachdulieu.get(position).getGioiHan());
        edtThoigianBDchuyendichoi.setText(Danhsachdulieu.get(position).getNgayBatdau()+" "+Danhsachdulieu.get(position).getThoiGianbatdau());
        edtThoigianchuyendichoi.setText(Danhsachdulieu.get(position).getThoiGianChuyendi());

        return viewRop;
    }

}
