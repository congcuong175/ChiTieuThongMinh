package com.example.chitieuthongminh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class TrangChu extends AppCompatActivity {
    public static ChipNavigationBar chipNavigationBar;
    public static LinearLayout lnlout_chipnv;
    Handler handler=new Handler();
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        chipNavigationBar=findViewById(R.id.chipNVGT);
        lnlout_chipnv=findViewById(R.id.lnlout_chipnv);
        chipNavigationBar.setItemSelected(R.id.home,true);

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.home:
                        Navigation.findNavController(TrangChu.this,R.id.nav_host_fragment_content_display_man).navigate(R.id.trangChuFragment);
                        break;
                    case R.id.activity:
                        Navigation.findNavController(TrangChu.this,R.id.nav_host_fragment_content_display_man).navigate(R.id.phanLoaiFragment);
                        break;
                    case R.id.favorites:

                       break;

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        lnlout_chipnv.setVisibility(View.VISIBLE);
    }


}