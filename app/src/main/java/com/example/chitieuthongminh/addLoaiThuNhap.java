package com.example.chitieuthongminh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chitieuthongminh.Adapter.IconAdapter;
import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;
import com.example.chitieuthongminh.Entity.LoaiThuNhapEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addLoaiThuNhap extends AppCompatActivity {
    GridView gridView;
    List<Integer> list;
    TextView tv_addthunhap;
    ImageView imv_icon_addthunhap;
    EditText edt_nhaptenloai_addthunhap;
    Dialog dialog;
    int chonanh=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loai_thu_nhap);
        showDialog();
        gridView=findViewById(R.id.list_icon_thunhap);
        imv_icon_addthunhap=findViewById(R.id.imv_icon_addthunhap);
        tv_addthunhap=findViewById(R.id.tv_add_thunhap_hoanthanh);
        edt_nhaptenloai_addthunhap=findViewById(R.id.edt_nhaptenloai_addthunhap);
        list=new ArrayList<>();
        list.add(R.drawable.icon);
        list.add(R.drawable.ic_alarm_clock);
        list.add(R.drawable.ic_backpack);
        list.add(R.drawable.ic_broccoli);
        list.add(R.drawable.ic_highlighter);
        list.add(R.drawable.ic_house);
        list.add(R.drawable.ic_school_bus);
        list.add(R.drawable.ic_sport_equipment);
        list.add(R.drawable.ic_tangerine);
        list.add(R.drawable.ic_accumulator);
        list.add(R.drawable.ic_delivery_bike);
        list.add(R.drawable.ic_doctor);
        list.add(R.drawable.ic_fries);
        list.add(R.drawable.ic_playground);
        list.add(R.drawable.ic_coke);
        list.add(R.drawable.ic_cake_slice);
        list.add(R.drawable.ic_sweets);
        list.add(R.drawable.ic_taxi);
        list.add(R.drawable.ic_sign);
        list.add(R.drawable.ic_soccer_player);
        IconAdapter adapter=new IconAdapter(addLoaiThuNhap.this,list,R.layout.item_icon);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chonanh=list.get(i);
                imv_icon_addthunhap.setImageResource(list.get(i));
            }
        });
        tv_addthunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                LoaiThuNhapEntity loaiThuNhapEntity=new LoaiThuNhapEntity();
                loaiThuNhapEntity.setTenLoai(edt_nhaptenloai_addthunhap.getText().toString());
                if(chonanh==0){
                    Toast.makeText(addLoaiThuNhap.this, "Bạn chưa chọn loại", Toast.LENGTH_SHORT).show();
                }
                else {
                    loaiThuNhapEntity.setIcon(chonanh);
                    ApiService.apiservice.themLTN(loaiThuNhapEntity).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            dialog.hide();
                            startActivity(new Intent(addLoaiThuNhap.this,TrangChu.class));
                            Toast.makeText(addLoaiThuNhap.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            dialog.hide();
                            Toast.makeText(addLoaiThuNhap.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });
    }
    public void showDialog(){
        dialog=new Dialog(addLoaiThuNhap.this);
        dialog.setContentView(R.layout.itemloaddata);
        dialog.setCancelable(false);
        Window window=dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        layoutParams.gravity= Gravity.CENTER;
        window.setAttributes(layoutParams);
    }
}