package com.example.caocsdl.travelbagapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickpham on 15/06/2016.
 */
public class custom_Chuyendi extends SQLiteOpenHelper {
    Context context;
    private static final String DB_Name2 = "dulich2";
    private static final String DB_Tieudechuyendichoi = "tieudechuyendichoi";
    private static final String DB_Noio = "odau";
    private static final String DB_Gioihan = "GioiHannoio";
    private static final String DB_Diahinh = "diahinhnoidulich";
    private static final String DB_Khihau = "Khihau";

    private static final String DB_TimStart = "timestart";
    private static final String DB_DateStart = "datestart";
    private static final String DB_TimeAll = "TimeAll";

    int Ketqua = 0;

    public custom_Chuyendi(Context context) {
        super(context,"chuyendichoi", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    public List<csdl> Danhsachchuyendichoi()
    {
        String sql = "select * from " + DB_Name2;
        List<csdl> DanhsachTravel = new ArrayList<csdl>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql,null);
        while(c.moveToNext())
        {
            csdl Huongdoituong = new csdl();
            Huongdoituong.setTieude(c.getString(c.getColumnIndex(DB_Tieudechuyendichoi)));
            Huongdoituong.setNoidi(c.getString(c.getColumnIndex(DB_Noio)));
            Huongdoituong.setGioiHan(c.getString(c.getColumnIndex(DB_Gioihan)));
            Huongdoituong.setDiahinh(c.getString(c.getColumnIndex(DB_Diahinh)));
            Huongdoituong.setKhihau(c.getString(c.getColumnIndex(DB_Khihau)));
            Huongdoituong.setThoiGianbatdau(c.getString(c.getColumnIndex(DB_TimStart)));
            Huongdoituong.setNgayBatdau(c.getString(c.getColumnIndex(DB_DateStart)));
            Huongdoituong.setThoiGianChuyendi(c.getString(c.getColumnIndex(DB_TimeAll)));
            DanhsachTravel.add(Huongdoituong);
        }
        return DanhsachTravel;
    }
    public boolean Themdulieu(ContentValues contentValuest)
    {
        SQLiteDatabase db = getWritableDatabase();
        int a = (int) db.insert(DB_Name2, null, contentValuest);
        Log.d("Ket qua", String.valueOf(a));
        if(a!=-1){
            Toast.makeText(context, "Them thanh cong", Toast.LENGTH_SHORT).show();
            //context.startActivity(intent);
            return true;
        }else
        {
            Toast.makeText(context, "Tiêu đề của chuyến du lịch đã tồn tại, làm ơn nhập lại tiêu đề mới ", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public void ThemBangdulieu(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
