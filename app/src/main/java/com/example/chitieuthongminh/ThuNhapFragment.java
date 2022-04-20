package com.example.chitieuthongminh;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chitieuthongminh.Adapter.LoaiChiTieuAdapter;
import com.example.chitieuthongminh.Adapter.LoaiThuNhapAdapter;
import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;
import com.example.chitieuthongminh.Entity.LoaiThuNhapEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThuNhapFragment extends Fragment {

    RecyclerView rcv_loaithunhap;
    LoaiThuNhapAdapter loaiThuNhapAdapter;
    List<LoaiThuNhapEntity> list;
    FloatingActionButton btn_addThuNhap;
    Handler handler2 =new Handler();
    Runnable runnable2;
    public static int idltnhap=0;
    public ThuNhapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_loaithunhap=view.findViewById(R.id.rcv_loaiThuNhap);
        btn_addThuNhap=view.findViewById(R.id.btn_add_thunhap);
        loaiThuNhapAdapter=new LoaiThuNhapAdapter();
        list=new ArrayList<>();
        loaiThuNhapAdapter.setData(list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_loaithunhap.setLayoutManager(layoutManager);
        rcv_loaithunhap.setAdapter(loaiThuNhapAdapter);
        btn_addThuNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),addLoaiThuNhap.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thu_nhap, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        handler2.postDelayed(runnable2=new Runnable() {
            @Override
            public void run() {
                getData();
                handler2.postDelayed(runnable2,3000);
            }
        },1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler2.removeCallbacks(runnable2);
    }

    public void getData(){
        list.clear();
        ApiService.apiservice.getDSLTN().enqueue(new Callback<List<LoaiThuNhapEntity>>() {
            @Override
            public void onResponse(Call<List<LoaiThuNhapEntity>> call, Response<List<LoaiThuNhapEntity>> response) {
                if(response.body()!=null){
                    for (LoaiThuNhapEntity lct:response.body()
                    ) {
                        list.add(lct);
                    }

                }
                loaiThuNhapAdapter.setData(list);
            }

            @Override
            public void onFailure(Call<List<LoaiThuNhapEntity>> call, Throwable t) {
                Log.e("LoiRoi",t.toString());
            }
        });
    }
}