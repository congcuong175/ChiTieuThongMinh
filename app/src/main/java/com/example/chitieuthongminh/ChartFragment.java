package com.example.chitieuthongminh;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.ChiTietChiTieu;
import com.example.chitieuthongminh.Entity.ChiTietEntity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChartFragment extends Fragment {

    List<ChiTietChiTieu> arrayList;
    int TongChi=0;
    int TongThu=0;
    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayList=new ArrayList<>();
        PieChart barChart=view.findViewById(R.id.barchart);
        ArrayList<PieEntry> visitors=new ArrayList<>();
        ApiService.apiservice.getCT().enqueue(new Callback<List<ChiTietEntity>>() {
            @Override
            public void onResponse(Call<List<ChiTietEntity>> call, Response<List<ChiTietEntity>> response) {
                if(response.body()!=null){
                    for (ChiTietEntity ct : response.body())
                    {
                        for(ChiTietChiTieu ctct: ct.getList()){
                            if(ctct.isChiTieu())
                            {
                                TongChi+=ctct.getTien();
                            }
                            else  {
                                TongThu+=ctct.getTien();
                            }
                        }
                    }
                    visitors.add(new PieEntry(TongChi,"Chi Tiêu"));
                    visitors.add(new PieEntry(TongThu,"Thu Nhập"));

                    PieDataSet barDataSet=new PieDataSet(visitors,"Visistors");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(16f);
                    PieData barData=new PieData(barDataSet);

                    barChart.setData(barData);
                    barChart.getDescription().setEnabled(false);
                    barChart.animate();
                }

            }

            @Override
            public void onFailure(Call<List<ChiTietEntity>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }
}