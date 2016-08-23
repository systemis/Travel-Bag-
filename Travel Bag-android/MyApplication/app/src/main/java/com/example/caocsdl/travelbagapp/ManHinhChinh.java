package com.example.caocsdl.travelbagapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ManHinhChinh extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    EditText editTextTenchuyendichoi;
    Dialog dialog;
    NavigationView navigationVie;
    LinearLayout lManhinhchinhFragment;
    FragmentTransaction fragmentTran;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    custom_Chuyendi Chuyendichoi = new custom_Chuyendi(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        Anhxa();

        fragmentTran = getSupportFragmentManager().beginTransaction();
        fragmentTran.add(R.id.frManhinhchinh,new fragmentManhinhchinh1(this));
        fragmentTran.commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Mo, R.string.Dong) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        dialog = new Dialog(ManHinhChinh.this);
        dialog.setTitle("Tiêu đề của chuyến đi chơi.");
        dialog.setContentView(R.layout.custom_dialog);
        editTextTenchuyendichoi = (EditText) dialog.findViewById(R.id.NTentieude);
        SukienNavigation();
    }
    public void Anhxa() {
        navigationVie = (NavigationView) findViewById(R.id.navi);
        drawerLayout = (DrawerLayout) findViewById(R.id.Manhinhchinh);
        toolbar = (Toolbar) findViewById(R.id.toolbarChao);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolber_cong, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.MenuTThem) {
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void nDthoat(View view) {
        dialog.cancel();
    }

    public void NDThem(View view) {
        String tenchuyendichoi = editTextTenchuyendichoi.getText().toString();
        if (tenchuyendichoi.isEmpty()) {
            Toast.makeText(ManHinhChinh.this, "Xin moi nhap vao khung ten chuyen di choi gium cho", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intentChuyendichoi = new Intent(ManHinhChinh.this, ManHinh_ThemChuydulich.class);
            Bundle bundledulieuChuyendichoi = new Bundle();
            bundledulieuChuyendichoi.putString("TenChuyenDiChoi", tenchuyendichoi);
            intentChuyendichoi.putExtra("dulieuChuyenDiChoiName", bundledulieuChuyendichoi);
            startActivity(intentChuyendichoi);
            dialog.cancel();
        }
    }
    public void SukienNavigation() {
        navigationVie.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.TrangchuMenu) {
                    fragmentTran = getSupportFragmentManager().beginTransaction();
                    fragmentTran.replace(R.id.frManhinhchinh, new fragmentManhinhchinh1(ManHinhChinh.this));
                    fragmentTran.commit();
                } else if (item.getItemId() == R.id.ThoitietMenu){
                    startActivity(new Intent(ManHinhChinh.this,Manhinhthoitiet.class));
                    drawerLayout.closeDrawers();}
                return true;
            }
        });
    }


}
