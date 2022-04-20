package com.example.chitieuthongminh;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chitieuthongminh.Adapter.LoaiChiTieuAdapter;
import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTieuFragment extends Fragment {

    RecyclerView rcv_loaichitieu;
    LoaiChiTieuAdapter loaiChiTieuAdapter;
    List<LoaiChiTieuEntity> list;
    FloatingActionButton btn_addChiTieu;
    Handler handler =new Handler();
    Runnable runnable;
    public static int idlctieu=0;
    public static int idchitietct=0;
    public ChiTieuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_loaichitieu=view.findViewById(R.id.rcv_loaichitieu);
        btn_addChiTieu=view.findViewById(R.id.btn_add_chitieu);
        loaiChiTieuAdapter=new LoaiChiTieuAdapter();
        list=new ArrayList<>();
        loaiChiTieuAdapter.setData(list);


        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_loaichitieu.setLayoutManager(layoutManager);
        rcv_loaichitieu.setAdapter(loaiChiTieuAdapter);
        btn_addChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),addLoaiChiTieu.class));
           }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chi_tieu, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable=new Runnable() {
            @Override
            public void run() {
                getData();
                handler.postDelayed(runnable,3000);
            }
        },1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    public void getData(){
        list.clear();
        ApiService.apiservice.getDSLCT().enqueue(new Callback<List<LoaiChiTieuEntity>>() {
            @Override
            public void onResponse(Call<List<LoaiChiTieuEntity>> call, Response<List<LoaiChiTieuEntity>> response) {

                if(response.body()!=null){
                    for (LoaiChiTieuEntity lct:response.body()
                    ) {
                        list.add(lct);
                    }

                }
                loaiChiTieuAdapter.setData(list);
            }

            @Override
            public void onFailure(Call<List<LoaiChiTieuEntity>> call, Throwable t) {

            }
        });
    }
}