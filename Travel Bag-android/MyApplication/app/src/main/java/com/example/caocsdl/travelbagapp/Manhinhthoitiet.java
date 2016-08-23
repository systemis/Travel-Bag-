package com.example.caocsdl.travelbagapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Manhinhthoitiet extends AppCompatActivity implements View.OnClickListener {

    Context context;
    TextView txtTenthanhpho,txtNhietdohientai,txtDoam,txtTocdogio;
    EditText editTextThanhphocantim;ImageButton imgTimkiem;
    String DuonglinkBandau = "http://api.openweathermap.org/data/2.5/forecast/daily?q="
            ,TenThanhpho,TenthanhphoHoanchinh,
            DuonglinkPhiasau = "&cnt=7&appid=be8d3e323de722ff78208a7dbb2dcd6f"
            ,Duonglinkhoanchinh;
    ImageButton btTimkiem;
    String TenthanhphoS,NhietdohientaiS;
    thoitiet TThoitiet = new thoitiet();
    NumberFormat format = new DecimalFormat("#0.0");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhthoitiet);
        setSupportActionBar((Toolbar) findViewById(R.id.Support));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Anhxa();
    }
    public void Anhxa()
    {
        editTextThanhphocantim = (EditText)findViewById(R.id.NhaptenThanhphoCantimThoitiet);
        imgTimkiem = (ImageButton)findViewById(R.id.TimkiemThoitiet);
        txtTenthanhpho = (TextView)findViewById(R.id.Tenthanhpho);
        txtNhietdohientai = (TextView)findViewById(R.id.Nhietdohientai);
        btTimkiem = (ImageButton)findViewById(R.id.TimkiemThoitiet);
        txtDoam = (TextView)findViewById(R.id.doam);
        txtTocdogio = (TextView)findViewById(R.id.tocdogio);
        btTimkiem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TenThanhpho = editTextThanhphocantim.getText().toString();
        TenthanhphoHoanchinh = TenThanhpho.replace(" ", "%20");
        Duonglinkhoanchinh = DuonglinkBandau+TenthanhphoHoanchinh+DuonglinkPhiasau;
        xulyLaydulieuAPI ah = new xulyLaydulieuAPI();
        ah.execute(Duonglinkhoanchinh);
    }
    public class xulyLaydulieuAPI extends AsyncTask<String,Void,String>
    {
        StringBuilder stringBuilderDulieu;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection ketnoi = (HttpURLConnection) url.openConnection();
                ketnoi.connect();
                InputStream stream = ketnoi.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(streamReader);
                String line = "";
                stringBuilderDulieu = new StringBuilder();
                while((line=reader.readLine())!=null)
                {
                    stringBuilderDulieu.append(line);
                }

                ketnoi.disconnect();stream.close();streamReader.close();reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilderDulieu.toString();
        }

        @Override
        protected void onPostExecute(String s){
            try {
                JSONObject objectAll = new JSONObject(s);
                JSONObject city = objectAll.getJSONObject("city");
                TThoitiet.setTenThanhpho(city.getString("name"));
                TThoitiet.setTenQuocgiacuathanhphodo(city.getString("country"));
                JSONArray listJson = objectAll.getJSONArray("list");
                for(int i =0;i<listJson.length();i++)
                {
                    JSONObject dulieuGan = listJson.getJSONObject(i);
                    JSONObject temp = dulieuGan.getJSONObject("temp");
                    double Dtemphientai = Double.parseDouble(temp.getString("day")) - 273.15;
                    double Dtocdogio = Double.parseDouble(dulieuGan.getString("speed"))*10;
                    TThoitiet.setTocdogio(String.valueOf(format.format(Dtocdogio)));
                    TThoitiet.setThoiTietHientai(String.valueOf(format.format(Dtemphientai)));
                }
                JSONObject dulieuGan1 = listJson.getJSONObject(2);
                double Ddoamuoc = Double.parseDouble(dulieuGan1.getString("humidity"));
                TThoitiet.setDoam(String.valueOf(format.format(Ddoamuoc)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            txtNhietdohientai.setText(TThoitiet.getThoiTietHientai()+"°c");
            txtTenthanhpho.setText(TThoitiet.getTenThanhpho()+", "+TThoitiet.getTenQuocgiacuathanhphodo());
            txtTocdogio.setText(TThoitiet.getTocdogio()+" km/h");
            txtDoam.setText(TThoitiet.getDoam()+"%");
            super.onPostExecute(s);
        }
    }
}
