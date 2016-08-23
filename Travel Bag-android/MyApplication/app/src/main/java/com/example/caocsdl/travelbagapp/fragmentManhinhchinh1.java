package com.example.caocsdl.travelbagapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickpham on 17/06/2016.
 */
public class fragmentManhinhchinh1 extends Fragment {
    ListView lstDanhsachchuyendi;List<csdl>Danhsach;String MangTieude[]={"du lich ba na","Moc chau du lich"};
    String DiaHinh[] = {"Nui","Song"};String MangThoigianBD[] = {"30/6/2016","21/6/20016"};
    Context context;


    //private static final String DB_Name = "dulich1";
    private static final String DB_Name = "dulich2";
    //private static final String DB_Tieudechuyendichoi = "tieudechuyendichoi";
    private static final String DB_Tieudechuyendichoi = "tieudechuyendichoi";
    private static final String DB_Noio = "odau";
    private static final String DB_Gioihan = "GioiHannoio";
    private static final String DB_Diahinh = "diahinhnoidulich";
    private static final String DB_Khihau = "Khihau";
    private static final String DB_TimStart = "timestart";
    private static final String DB_DateStart = "datestart";
    private static final String DB_TimeAll = "TimeAll";


    public fragmentManhinhchinh1(Context context)
    {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_man_hinh_chinh1,container,false);
        Createtable();
        lstDanhsachchuyendi = (ListView)view.findViewById(R.id.listDanhsachchuyendichoi);
        Danhsach = new custom_Chuyendi(context).Danhsachchuyendichoi();
        Toast.makeText(context,Danhsach.get(0).getNoidi(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,Danhsach.get(1).getNgayBatdau(),Toast.LENGTH_SHORT).show();
        /*for (int i = 0; i < MangTieude.length; i++) {
            csdl csdl = new csdl();
            csdl.setTieude(MangTieude[i]);
            csdl.setThoiGianbatdau(MangThoigianBD[i]);
            csdl.setDiahinh(DiaHinh[i]);
            Danhsach.add(csdl);
        }*/

        customListView AB = new customListView(context, R.layout.custom_lisview, Danhsach);
        lstDanhsachchuyendi.setAdapter(AB);
        lstDanhsachchuyendi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itManhinhxedulieuchuyendi = new Intent(context,xemdulieuchuyendichoi.class);
                Bundle bdldulieumanhinh = new Bundle();
                bdldulieumanhinh.putInt("Vitri",position);
                itManhinhxedulieuchuyendi.putExtra("dulieumanhinh",bdldulieumanhinh);
                startActivity(itManhinhxedulieuchuyendi);
            }
        });
        return view;
    }

    private void Createtable(){
        custom_Chuyendi Chuyendichoi = new custom_Chuyendi(context);
        Chuyendichoi.ThemBangdulieu("create table if not exists " + DB_Name + " ( "
                + DB_Tieudechuyendichoi + " text primary key, "
                + DB_Noio + " text, "
                + DB_Gioihan + " text, "
                + DB_Diahinh + " text, "
                + DB_Khihau + " text, "
                + DB_TimStart + " text, "
                + DB_DateStart + " text, "
                + DB_TimeAll + " text" + ")");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
