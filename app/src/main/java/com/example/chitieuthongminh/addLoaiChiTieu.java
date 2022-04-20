package com.example.chitieuthongminh;

import static com.example.chitieuthongminh.TrangChu.chipNavigationBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chitieuthongminh.Adapter.IconAdapter;
import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addLoaiChiTieu extends AppCompatActivity {
    GridView gridView;
    List<Integer> list;
    ImageView imv_icon_addchitieu;
    TextView tv_addchitieu;
    EditText edt_nhaptenloai_addchitieu;
    int chonanh=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loai_chi_tieu);
        gridView=findViewById(R.id.list_icon);
        imv_icon_addchitieu=findViewById(R.id.imv_icon_addchitieu);
        tv_addchitieu=findViewById(R.id.btn_hoanthanh_add);
        edt_nhaptenloai_addchitieu=findViewById(R.id.edt_nhaptenloai_addchitieu);
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
        IconAdapter adapter=new IconAdapter(addLoaiChiTieu.this,list,R.layout.item_icon);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chonanh=list.get(i);
                imv_icon_addchitieu.setImageResource(list.get(i));
            }
        });
        tv_addchitieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiChiTieuEntity loaiChiTieuEntity=new LoaiChiTieuEntity();
                loaiChiTieuEntity.setTenLoai(edt_nhaptenloai_addchitieu.getText().toString());
                if(chonanh==0){
                    Toast.makeText(addLoaiChiTieu.this, "Bạn chưa chọn loại", Toast.LENGTH_SHORT).show();
                }
                else {
                    loaiChiTieuEntity.setIcon(chonanh);
                }
                ApiService.apiservice.themLCT(loaiChiTieuEntity).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        startActivity(new Intent(addLoaiChiTieu.this,TrangChu.class));
                        Toast.makeText(addLoaiChiTieu.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(addLoaiChiTieu.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}