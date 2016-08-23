package com.example.caocsdl.travelbagapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class themdodacchochuyendichoiDathem extends AppCompatActivity {

    private String DiadiemnoidulichS,gioihanchuyendiS, KhihaunoidulichS, DiahinhnoidulichS, ThoigiandulichS,
            TieudechuyendichoiS,ThoigianTime,ThoigianDate;
    String[] Manggioihanchuyendichoi = {"Trong nước", "Ngoài nước"};
    String[] MangKhihau = {"Lạnh", "Nóng", "ôn hoà"};
    String[] MangDiahinh = {"Sông,Suối", "Núi ", "Khu nghỉ dưỡng ", "Biển"};
    String[] MangsoNgaydi = {"3 ngay", "4 ngay", "10 ngay", "20 ngay", "Dài hơn một tháng"};
    int Gioihan, Khihau, Diahinh, Thoigian;

    private ListView lsDanhsachOfbalo;
    private ImageView imgHinhanhnoidi;
    private TextView txtNamewhere,txtLoaidiahinhnoidi,txtLoaikhihaunoidi;
    private Button btXongchuyendichoi;

    private String Tenbangdulieu;
    private static final String DB_DODACBALO = "DodacBalo";
    //Thong tin chuyen di choi
    private static final String DB_Name = "dulich2";
    private static final String DB_Tieudechuyendichoi = "tieudechuyendichoi";
    private static final String DB_Noio = "odau";
    private static final String DB_Gioihan = "GioiHannoio";
    private static final String DB_Diahinh = "diahinhnoidulich";
    private static final String DB_Khihau = "Khihau";
    private static final String DB_TimStart = "timestart";
    private static final String DB_DateStart = "datestart";
    private static final String DB_TimeAll = "TimeAll";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themdodacchochuyendichoi_dathem);
        Toolbar toolbarB = (Toolbar) findViewById(R.id.toolbarThembalo);
        setSupportActionBar(toolbarB);
        Bundle bundleLaygoidulieu = getIntent().getBundleExtra("dulieuManhinhthemchuyendulich");
        DiadiemnoidulichS = bundleLaygoidulieu.getString("Diadiemnoidulich");
        //Toast.makeText(themdodacchochuyendichoiDathem.this,DiahinhnoidulichS, Toast.LENGTH_SHORT).show();
        TieudechuyendichoiS = bundleLaygoidulieu.getString("Tieudechuyendichoi");
        gioihanchuyendiS = bundleLaygoidulieu.getString("GioiHanchuyendichoi");
        KhihaunoidulichS = bundleLaygoidulieu.getString("Khihaunoidulich");
        DiahinhnoidulichS = bundleLaygoidulieu.getString("Diahinhnoidulich");
        ThoigiandulichS = bundleLaygoidulieu.getString("Thoigiandidulich");
        ThoigianTime = bundleLaygoidulieu.getString("ThoigianTime");
        ThoigianDate = bundleLaygoidulieu.getString("ThoigianDate");
        getSupportActionBar().setTitle(TieudechuyendichoiS);
        Toast.makeText(themdodacchochuyendichoiDathem.this,ThoigianTime+" "+ThoigianDate, Toast.LENGTH_SHORT).show();;
        Kiemtra();
        Anhxa();
        //Toast.makeText(themdodacchochuyendichoiDathem.this, gioihanchuyendiS, Toast.LENGTH_SHORT).show();
        XulyAdapter();
        TaoNoichuabalo();
    }

    String mangDodacCodinh[] = {"Thuốc men","Giấy tờ tuỳ thân ","Điện thoại ","Máy nghe nhạc ","Sách kỹ năng sống ",
                                "Máy ảnh(Nếu có ) ","Sạc dữ phòng và cáp ","Giày thể thao ","Máy tính "};
    public void Kiemtra() {
        for (int i = 0; i < Manggioihanchuyendichoi.length; i++) {
            if (gioihanchuyendiS.equals(Manggioihanchuyendichoi[i])) Gioihan = i;
        }
        for (int i = 0; i < MangKhihau.length; i++) {
            if (KhihaunoidulichS.equals(MangKhihau[i])) {
                Khihau = i;
            }
        }
        for (int i = 0; i < MangDiahinh.length; i++) {
            if (DiahinhnoidulichS.equals(MangDiahinh[i])) {
                Diahinh = i;
            }
        }
        for (int i = 0; i < MangsoNgaydi.length; i++) {
            if (ThoigiandulichS.equals(MangsoNgaydi[i])) {
                Thoigian = i;
            }
        }
    }

    String tebBangNameBalo;
    public void Anhxa() {
        lsDanhsachOfbalo = (ListView) findViewById(R.id.lsDanhsachTrongBlo);
        imgHinhanhnoidi = (ImageView)findViewById(R.id.hinhanhnoidi);
        txtNamewhere = (TextView)findViewById(R.id.tenWhere);
        txtLoaidiahinhnoidi = (TextView)findViewById(R.id.loaidiahinh);
        txtLoaikhihaunoidi = (TextView)findViewById(R.id.loaikhihau);
        btXongchuyendichoi = (Button)findViewById(R.id.btXongchuyendichoi);

        txtNamewhere.setText(DiadiemnoidulichS);
        txtLoaidiahinhnoidi.setText(DiahinhnoidulichS);
        txtLoaikhihaunoidi.setText(KhihaunoidulichS);

        Toast.makeText(themdodacchochuyendichoiDathem.this,String.valueOf(Khihau), Toast.LENGTH_SHORT).show();
        if(Diahinh==0){imgHinhanhnoidi.setImageResource(R.mipmap.song);}
        if(Diahinh==1){imgHinhanhnoidi.setImageResource(R.mipmap.mountainview);}
        if(Diahinh==2){imgHinhanhnoidi.setImageResource(R.mipmap.resortview);}

        Tenbangdulieu = TieudechuyendichoiS+"balo";


    }
    // Anh xa va xu ly adapter listview
    public void XulyAdapter() {
        int Soluongdodung;

       if(Khihau==0&&Diahinh==0||Diahinh==3){
            String Mangdodac[] = {"Áo gió ","Áo ấm ","Quần dài ","Đèn pin ","Dây thừng ","Kính râm ",
                    "Lều ","Thực phẩm dự trữ ","Nước lọc ","Aó phao cứu hộ ","Quẹt diêm "};
            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length+Mangdodac.length];
            for(int i =0;i<mangDodacCodinh.length;i++){MangdodacHoanchinh[i]=mangDodacCodinh[i];};
           for(int i = mangDodacCodinh.length;i<MangdodacHoanchinh.length;i++) {
               MangdodacHoanchinh[i] = Mangdodac[i-mangDodacCodinh.length];}
           ganadapterforlistview(MangdodacHoanchinh);
        }
        
        if(Diahinh==2&&Khihau==0)
        {
            String Mangdodac[] = {"Áo gió ","Áo ấm ","Quần dài ","Kính râm ", "Dép lê(Thoải mái khi du lịch ) "};

            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length+Mangdodac.length];
            for(int i =0;i<mangDodacCodinh.length;i++){MangdodacHoanchinh[i]=mangDodacCodinh[i];};
            for(int i = mangDodacCodinh.length;i<MangdodacHoanchinh.length;i++) {
                MangdodacHoanchinh[i] = Mangdodac[i-mangDodacCodinh.length];}
            ganadapterforlistview(MangdodacHoanchinh);
        }
        if(Diahinh==2&&Khihau==1||Khihau==2)
        {
            String Mangdodac[] = {"Áo tay ngắn ","Áo thun mỏng ","Quần đùi ",
                    "Đèn pin ","Kính râm ", "Dép lê(Thoải mái khi du lịch ) "};
            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length+Mangdodac.length];
            for(int i =0;i<mangDodacCodinh.length;i++){MangdodacHoanchinh[i]=mangDodacCodinh[i];};
            for(int i = mangDodacCodinh.length;i<MangdodacHoanchinh.length;i++) {
                MangdodacHoanchinh[i] = Mangdodac[i-mangDodacCodinh.length];}
            ganadapterforlistview(MangdodacHoanchinh);
        }
        if(Khihau==1 && Diahinh==0||Diahinh==3){
            String Mangdodac[] = {"Áo tay ngắn ","Áo thun mỏng ","Quần đùi  ","Đèn pin ","Dây thừng ","Kính râm ",
                    "Lều ","Bình nước giữ nhiệt ","Thực phẩm dự trữ "
                    ,"Nước lọc ","Aó phao cứu hộ ","Quẹt diêm "};
            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length+Mangdodac.length];
            for(int i =0;i<mangDodacCodinh.length;i++){MangdodacHoanchinh[i]=mangDodacCodinh[i];};
            for(int i = mangDodacCodinh.length;i<MangdodacHoanchinh.length;i++) {
                MangdodacHoanchinh[i] = Mangdodac[i-mangDodacCodinh.length];}
            ganadapterforlistview(MangdodacHoanchinh);
        }
        if(Khihau==2&&Diahinh==0||Diahinh==3) {
            String Mangdodac[] = {"Áo tay ngắn ", "Quần đùi ", "Đèn pin", "Dây thừng ",
                    "Lều ", "Bình nước giữ nhiệt ", "Thực phẩm dự trữ ", "Nước lọc ","Aó phao cứu hộ ","Quẹt diêm "};
            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length + Mangdodac.length];
            for (int i = 0; i < mangDodacCodinh.length; i++) {
                MangdodacHoanchinh[i] = mangDodacCodinh[i];}
            for (int i = mangDodacCodinh.length; i < MangdodacHoanchinh.length; i++) {
                MangdodacHoanchinh[i] = Mangdodac[i - mangDodacCodinh.length];}
            ganadapterforlistview(MangdodacHoanchinh);
        }
        if(Khihau==2&&Diahinh==1) {
            String Mangdodac[] = {"Áo tay ngắn ", "Quần đùi ", "Đèn pin", "Dây thừng ",
                    "Lều ", "Bình nước giữ nhiệt ", "Thực phẩm dự trữ ", "Nước lọc ","Quẹt diêm "};
            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length + Mangdodac.length];
            for (int i = 0; i < mangDodacCodinh.length; i++) {
                MangdodacHoanchinh[i] = mangDodacCodinh[i];}
            for (int i = mangDodacCodinh.length; i < MangdodacHoanchinh.length; i++) {
                MangdodacHoanchinh[i] = Mangdodac[i - mangDodacCodinh.length];}
            ganadapterforlistview(MangdodacHoanchinh);
        }
        if(Khihau==0&&Diahinh==1) {
            String Mangdodac[] = {"Áo gió ","Áo ấm ","Quần dài ","Đèn pin ","Dây thừng ","Kính râm ",
                    "Lều ","Thực phẩm dự trữ ","Nước lọc ","Quẹt diêm "};
            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length + Mangdodac.length];
            for (int i = 0; i < mangDodacCodinh.length; i++) {
                MangdodacHoanchinh[i] = mangDodacCodinh[i];}
            for (int i = mangDodacCodinh.length; i < MangdodacHoanchinh.length; i++) {
                MangdodacHoanchinh[i] = Mangdodac[i - mangDodacCodinh.length];}
            ganadapterforlistview(MangdodacHoanchinh);
        }
        if(Khihau==1&&Diahinh==1) {
            String Mangdodac[] = {"Áo tay ngắn ","Áo thun mỏng ","Quần đùi  ","Đèn pin ","Dây thừng ","Kính râm ",
                    "Lều ","Bình nước giữ nhiệt ","Thực phẩm dự trữ "
                    ,"Nước lọc ","Quẹt diêm "};
            String MangdodacHoanchinh[] = new String[mangDodacCodinh.length + Mangdodac.length];
            for (int i = 0; i < mangDodacCodinh.length; i++) {
                MangdodacHoanchinh[i] = mangDodacCodinh[i];}
            for (int i = mangDodacCodinh.length; i < MangdodacHoanchinh.length; i++) {
                MangdodacHoanchinh[i] = Mangdodac[i - mangDodacCodinh.length];}
            ganadapterforlistview(MangdodacHoanchinh);
        }
    }
    List<csdlDanhsachcacthutrongbalo> Danhsach;
    public void ganadapterforlistview(String[] MangdodacHoanchinh)
    {
       Danhsach = new ArrayList<csdlDanhsachcacthutrongbalo>();
        for(int i =0;i<MangdodacHoanchinh.length;i++)
        {
            csdlDanhsachcacthutrongbalo Huongdoituong = new csdlDanhsachcacthutrongbalo();
            Huongdoituong.setDodac(MangdodacHoanchinh[i]);
            Danhsach.add(Huongdoituong);
        }
        custom_lisview_ofbalo ofbalo = new custom_lisview_ofbalo(getApplicationContext(),R.layout.custom_listview_ofbalo,Danhsach);
        lsDanhsachOfbalo.setAdapter(ofbalo);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_danhsachbalo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void TaoNoichuabalo()
    {

    }
    private custom_Chuyendi Chuyendi = new custom_Chuyendi(this);
    public void XongchuyendiOnclick(View view)
    {
        if(Chuyendi.Themdulieu(Thembangtaihien(TieudechuyendichoiS))==false)
        {
            Dialog dialogThaydoitieudechuyendichoi = new Dialog(themdodacchochuyendichoiDathem.this);
            dialogThaydoitieudechuyendichoi.setTitle("Tiêu đề chuyến đi chơi mới ");
            dialogThaydoitieudechuyendichoi.setContentView(R.layout.luachonlaichuyendichoi);
            dialogThaydoitieudechuyendichoi.show();
            EditText edtCapnhaptenmoi = (EditText)dialogThaydoitieudechuyendichoi.findViewById(R.id.nhaptieudemoicuachuyendichoi);
            Button btCapnhaplaichuyendichoithatbai = (Button)dialogThaydoitieudechuyendichoi.findViewById(R.id.capnhaplai);
            Button btXoachuyendichoidathem = (Button)dialogThaydoitieudechuyendichoi.findViewById(R.id.huychuyendichoidathem);
            Button btXoachuyendichoihientai = (Button)dialogThaydoitieudechuyendichoi.findViewById(R.id.huychuyendichoihientai);
            btCapnhaplaichuyendichoithatbai.setOnClickListener(new xulykhithemthatbai(edtCapnhaptenmoi,dialogThaydoitieudechuyendichoi));
            btXoachuyendichoidathem.setOnClickListener(new xulykhithemthatbai(edtCapnhaptenmoi,dialogThaydoitieudechuyendichoi));
            btXoachuyendichoihientai.setOnClickListener(new xulykhithemthatbai(edtCapnhaptenmoi,dialogThaydoitieudechuyendichoi));
        }else
        {
            btXongchuyendichoi.setClickable(false);
            startActivity(new Intent(themdodacchochuyendichoiDathem.this,ManHinhChinh.class));
        }
    }
    public void HuychuyendiOnclick(View v)
    {
        startActivity(new Intent(themdodacchochuyendichoiDathem.this,ManHinhChinh.class));
        finish();
    }

    public ContentValues Thembangtaihien(String tieudechuyendichoi)
    {
        ContentValues values = new ContentValues();
        values.put(DB_Tieudechuyendichoi, tieudechuyendichoi);
        values.put(DB_Noio, DiadiemnoidulichS);
        values.put(DB_Gioihan, gioihanchuyendiS);
        values.put(DB_Diahinh, DiahinhnoidulichS);
        values.put(DB_Khihau, KhihaunoidulichS);
        values.put(DB_TimStart, ThoigianTime);
        values.put(DB_DateStart,ThoigianDate);
        values.put(DB_TimeAll, ThoigiandulichS);
        return values;
    }

    private class xulykhithemthatbai implements View.OnClickListener
    {
        Dialog dialogGiaodich;
        EditText edtCapnhapchuyendichoi;
        public xulykhithemthatbai(EditText edtCapnhap,Dialog A)
        {
            this.edtCapnhapchuyendichoi = edtCapnhap;
            this.dialogGiaodich = A;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.huychuyendichoihientai:
                    startActivity(new Intent(themdodacchochuyendichoiDathem.this,ManHinhChinh.class));
                    dialogGiaodich.cancel();
                    finish();
                    break;
                case R.id.capnhaplai:
                    if(edtCapnhapchuyendichoi.getText().toString().isEmpty())
                    {
                        edtCapnhapchuyendichoi.setError("Mục này không được trống");
                    }else {
                        if (Chuyendi.Themdulieu(Thembangtaihien(edtCapnhapchuyendichoi.getText().toString()))) {
                            Toast.makeText(themdodacchochuyendichoiDathem.this, "Cap nhap thanh cong", Toast.LENGTH_SHORT).show();
                            dialogGiaodich.cancel();
                            btXongchuyendichoi.setClickable(false);
                            startActivity(new Intent(themdodacchochuyendichoiDathem.this, ManHinhChinh.class));
                            Toast.makeText(themdodacchochuyendichoiDathem.this, Thembangtaihien(edtCapnhapchuyendichoi.getText().toString()).getAsString(DB_Tieudechuyendichoi), Toast.LENGTH_SHORT).show();
                        }else {
                            do{
                                edtCapnhapchuyendichoi.setError("Tiêu đề chuyến đi chơi này đã tồn tại, làm ơn nhập lại");
                                Chuyendi.Themdulieu(Thembangtaihien(edtCapnhapchuyendichoi.getText().toString()));
                            }while(Chuyendi.Themdulieu(Thembangtaihien(edtCapnhapchuyendichoi.getText().toString()))!=true
                                    &&edtCapnhapchuyendichoi.getError().toString().isEmpty()==true);
                        }
                    }
                    break;

            }
        }
    }
}
