package com.example.caocsdl.travelbagapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nickpham on 17/06/2016.
 */
public class json {
    String dulieu;
    public json(String dulieu)
    {
        this.dulieu = dulieu;
    }
    public thoitiet Laydulieu()
    {
        thoitiet weather = new thoitiet();
        try {
            JSONObject object = new JSONObject(dulieu);
            JSONObject city = object.getJSONObject("city");
            weather.setTenThanhpho(city.getString("name"));
            weather.setTenQuocgiacuathanhphodo(city.getString("country"));
            JSONArray listJson = object.getJSONArray("list");
            double day = 0;
            for(int i =0;i<listJson.length();i++)
            {
                JSONObject dayli = listJson.getJSONObject(i);
                JSONObject temp = dayli.getJSONObject("temp");
                day = Double.parseDouble(temp.getString("day"));
            }
            float day2 = (float) (day - 273.15);
            weather.setThoiTietHientai(String.valueOf(day2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weather;
    }

}
