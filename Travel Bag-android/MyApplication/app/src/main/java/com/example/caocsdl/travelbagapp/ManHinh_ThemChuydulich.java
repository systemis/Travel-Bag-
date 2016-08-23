package com.example.caocsdl.travelbagapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManHinh_ThemChuydulich extends AppCompatActivity{
    TextView txtThoigianTime,txtThoigianDate;
    Spinner gioihanchuyendichoi,KhihaunoioSpinner,diahinhchuyendichoi,spinnerSongaydi;
    String[] Manggioihanchuyendichoi = {"Trong nước","Ngoài nước"};String[] MangKhihau = {"Lạnh","Nóng","ôn hoà"};
    String[] MangDiahinh = {"Sông,Suối","Núi ","Khu nghỉ dưỡng ", "Biển"};String[] MangsoNgaydi = {"3 ngay","4 ngay","10 ngay","20 ngay","Dài hơn một tháng"};
    String SGioihanchuyendi,Skhihaunoidulich,Sdiahinhnoidulich,StieudeChuyendichoi,SSongaydichoi,SWhere;
    custom_Chuyendi Chuyendichoi = new custom_Chuyendi(this);
    String thoigianTime,thoigianDate;
    EditText editTextWhere;

    static final int Dialog_id_date = 0;
    int year_x,month_x,day_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh__them_chuydulich);
        Bundle bundleLaydulieuManhinhchinhTenchuyendichoi = getIntent().getBundleExtra("dulieuChuyenDiChoiName");
        StieudeChuyendichoi = bundleLaydulieuManhinhchinhTenchuyendichoi.getString("TenChuyenDiChoi");
        Anhxa(StieudeChuyendichoi);
        GanAdapterchocacView();
        sukiencuacacSpinner();
    }
    public void GanAdapterchocacView()
    {

        custom_danhsach danhsach = new custom_danhsach(this,R.layout.custom_spinner_list,Manggioihanchuyendichoi);
        gioihanchuyendichoi.setAdapter(danhsach);
        custom_danhsach danhsachKhihau = new custom_danhsach(this,R.layout.custom_spinner_list,MangKhihau);
        KhihaunoioSpinner.setAdapter(danhsachKhihau);
        custom_danhsach danhsachDiahinh = new custom_danhsach(this,R.layout.custom_spinner_list,MangDiahinh);
        diahinhchuyendichoi.setAdapter(danhsachDiahinh);
        custom_danhsach danhsachSongaydi = new custom_danhsach(this,R.layout.custom_spinner_list,MangsoNgaydi);
        spinnerSongaydi.setAdapter(danhsachSongaydi);
    }
    public void sukiencuacacSpinner()
    {

        diahinhchuyendichoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sdiahinhnoidulich = MangDiahinh[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        KhihaunoioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Skhihaunoidulich = MangKhihau[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gioihanchuyendichoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SGioihanchuyendi = Manggioihanchuyendichoi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSongaydi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SSongaydichoi = MangsoNgaydi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    Dialog dialogTime,dialogDate;TimePicker timePicker;DatePicker datePicker;Calendar calendar;
    public void Anhxa(String tieude)
    {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarChao1));
        getSupportActionBar().setTitle(tieude);
        gioihanchuyendichoi = (Spinner)findViewById(R.id.NGioihanchyendi);
        KhihaunoioSpinner = (Spinner)findViewById(R.id.KhiHaunoio);
        diahinhchuyendichoi = (Spinner)findViewById(R.id.diahinhnoidulich);
        spinnerSongaydi = (Spinner)findViewById(R.id.spinnerSongaydi);
        txtThoigianTime = (TextView)findViewById(R.id.timeH);
        txtThoigianDate = (TextView)findViewById(R.id.timeD);
        editTextWhere = (EditText)findViewById(R.id.NDi);
        SWhere = editTextWhere.getText().toString();

        dialogTime = new Dialog(ManHinh_ThemChuydulich.this);
        dialogTime.setTitle("Edit time");
        dialogTime.setContentView(R.layout.dialog_timeanddatepicker);
        timePicker = (TimePicker)dialogTime.findViewById(R.id.TimePic);
        timePicker.setIs24HourView(true);
        dialogDate = new Dialog(ManHinh_ThemChuydulich.this);
        dialogDate.setTitle("Edit date");
        dialogDate.setContentView(R.layout.datpick);
        datePicker = (DatePicker) dialogDate.findViewById(R.id.datePickerDailog);

        calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(calendar.MONTH);
        day_x =  calendar.get(calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd_HH mm ss");
        String currentDateandTime = sdf.format(new Date());

        thoigianTime = String.valueOf(new SimpleDateFormat("HH:mm").format(new Date()));
        thoigianDate = String.valueOf(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        txtThoigianTime.setText(thoigianTime);
        txtThoigianDate.setText(thoigianDate);
        laythoigian();

    }
    public class custom_danhsach extends BaseAdapter
    {
        Context context;int Layout;String[] Mang;
        public custom_danhsach(Context context,int Layout,String[] Mang) {
            this.context = context;this.Layout = Layout;this.Mang = Mang;
        }
        @Override
        public int getCount() {
            return Mang.length;
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
            convertView = inflater.inflate(Layout,parent,false);
            TextView txt = (TextView)convertView.findViewById(R.id.SpinnerChuyendichoitxt);
            txt.setText(Mang[position]);
            return convertView;
        }
    }
    public void ThemEdit(View view)
    {
        final Dialog dialog = new Dialog(ManHinh_ThemChuydulich.this);
        dialog.setTitle("Edit time");
        dialog.setContentView(R.layout.luachonkieuedit);
        dialog.show();
        Button btDate = (Button) dialog.findViewById(R.id.luachonDate);
        Button btTime = (Button) dialog.findViewById(R.id.luachonh);
        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Dialog_id_date);
            }
        });
        btTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTime.show();
            }
        });
    }
    public void laythoigian() {
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                thoigianTime = String.valueOf(hourOfDay + ":" + new SimpleDateFormat("HH:mm").format(minute));
            }
        });
        Button buttonTime = (Button) dialogTime.findViewById(R.id.themTime);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManHinh_ThemChuydulich.this,thoigianTime, Toast.LENGTH_SHORT).show();
                dialogTime.cancel();
                txtThoigianTime.setText(thoigianTime);
            }
        });

        datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                thoigianDate = String.valueOf(year+"/"+monthOfYear+"/"+dayOfMonth);
            }
        });
        Button buttonDate = (Button) dialogDate.findViewById(R.id.DialogDateXong);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate.cancel();
                txtThoigianDate.setText(thoigianDate);
            }
        });
    }

    public void tieptuc(View view)
    {
        Toast.makeText(ManHinh_ThemChuydulich.this,editTextWhere.getText().toString(), Toast.LENGTH_SHORT).show();
        if(editTextWhere.getText().toString().isEmpty())
        {
            editTextWhere.setError("Làm ơn nhập địa điểm bạn đi du lịch ");
        }else {
            /* Tối ưu bằng cách di chuyển đoạn này sang màn hình cuối cùng
            ContentValues values = new ContentValues();
            values.put(DB_Tieudechuyendichoi, StieudeChuyendichoi);
            values.put(DB_Noio, editTextWhere.getText().toString());
            values.put(DB_Gioihan, SGioihanchuyendi);
            values.put(DB_Diahinh, Sdiahinhnoidulich);
            values.put(DB_Khihau, Skhihaunoidulich);
            values.put(DB_TimStart, txtThoigianTime + " " + txtThoigianDate);
            values.put(DB_TimeAll, SSongaydichoi);
            */
            Intent themdanhsachbalo = new Intent(getApplicationContext(), themdodacchochuyendichoiDathem.class);
            Bundle bundleDulieuthem = new Bundle();
            bundleDulieuthem.putString("Tieudechuyendichoi",StieudeChuyendichoi);
            bundleDulieuthem.putString("GioiHanchuyendichoi", SGioihanchuyendi);
            bundleDulieuthem.putString("Khihaunoidulich", Skhihaunoidulich);
            bundleDulieuthem.putString("Diahinhnoidulich", Sdiahinhnoidulich);
            bundleDulieuthem.putString("Thoigiandidulich", SSongaydichoi);
            bundleDulieuthem.putString("Diadiemnoidulich", editTextWhere.getText().toString());
            bundleDulieuthem.putString("ThoigianTime",thoigianTime);
            bundleDulieuthem.putString("ThoigianDate",thoigianDate);
            themdanhsachbalo.putExtra("dulieuManhinhthemchuyendulich", bundleDulieuthem);
            startActivity(themdanhsachbalo);
            finish();
        }
    }

    protected Dialog onCreateDialog(int id)
    {
        if(id==Dialog_id_date)
            return new DatePickerDialog(ManHinh_ThemChuydulich.this,dpClick,year_x,month_x,day_x);
            return null;
    }
    private DatePickerDialog.OnDateSetListener dpClick = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;month_x=monthOfYear;day_x=dayOfMonth;
            thoigianDate = year_x+"/"+month_x+"/"+day_x;
            txtThoigianDate.setText(year_x+"/"+month_x+"/"+day_x);
            Toast.makeText(ManHinh_ThemChuydulich.this,String.valueOf(year_x+"/"+month_x+"/"+day_x), Toast.LENGTH_SHORT).show();
        }
    };
}
