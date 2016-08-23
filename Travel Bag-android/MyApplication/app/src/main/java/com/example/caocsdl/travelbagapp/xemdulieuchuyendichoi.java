package com.example.caocsdl.travelbagapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class xemdulieuchuyendichoi extends AppCompatActivity {

    private LinearLayout lnHienthifragmentthongtin;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbarXemdulieu;
    RelativeLayout Manhinhanhbiachuyendichoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdulieuchuyendichoi);
        toolbarXemdulieu = (Toolbar)findViewById(R.id.toolbarXemdulieu);
        setSupportActionBar(toolbarXemdulieu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Anhxa();
        Bundle bdlLaydulieumanhinhchinh = getIntent().getBundleExtra("dulieumanhinh");
        int position = bdlLaydulieumanhinhchinh.getInt("Vitri");
        Toast.makeText(xemdulieuchuyendichoi.this,String.valueOf(position), Toast.LENGTH_SHORT).show();
        fragmentdulieuchuyendichoi AboutChuyendichoi = new fragmentdulieuchuyendichoi(this,position);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.hienthifragmentmanhinhchuyendivsbalochuyendi,AboutChuyendichoi);
        fragmentTransaction.commit();
        Gananhbia(new custom_Chuyendi(this).Danhsachchuyendichoi().get(position).getDiahinh());
    }
    public void Anhxa()
    {
        lnHienthifragmentthongtin = (LinearLayout)findViewById(R.id.hienthifragmentmanhinhchuyendivsbalochuyendi);
        Manhinhanhbiachuyendichoi = (RelativeLayout)findViewById(R.id.anhbiaphanthongtin);
    }
    private void Gananhbia(String Diahinh)
    {
        if(Diahinh.equals("Sông,Suối")){Manhinhanhbiachuyendichoi.setBackgroundResource(R.mipmap.song);}
        if(Diahinh.equals("Biển")){Manhinhanhbiachuyendichoi.setBackgroundResource(R.mipmap.bien);}
        if(Diahinh.equals("Khu nghỉ dưỡng ")){Manhinhanhbiachuyendichoi.setBackgroundResource(R.mipmap.resortview);}
        if(Diahinh.equals("Núi ")){Manhinhanhbiachuyendichoi.setBackgroundResource(R.mipmap.mountainview);}
    }
}
