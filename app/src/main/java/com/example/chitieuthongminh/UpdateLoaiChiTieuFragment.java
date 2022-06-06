package com.example.chitieuthongminh;

import static com.example.chitieuthongminh.ChiTieuFragment.idlctieu;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class UpdateLoaiChiTieuFragment extends Fragment {
    GridView gridView;
    List<Integer> list;
    ImageView imv_icon_udchitieu;
    TextView tv_udchitieu;
    EditText edt_nhaptenloai_udchitieu;
    LoaiChiTieuEntity loaiChiTieuEntity=new LoaiChiTieuEntity();
    int chonanh=0;
    public UpdateLoaiChiTieuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TrangChu tc= (TrangChu) getActivity();
        tc.getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).navigate(R.id.action_updateLoaiChiTieuFragment_to_phanLoaiFragment);
            }
        });
        gridView=view.findViewById(R.id.list_icon_ud);
        imv_icon_udchitieu=view.findViewById(R.id.imv_icon_udchitieu);
        tv_udchitieu=view.findViewById(R.id.btn_hoanthanh_ud);
        edt_nhaptenloai_udchitieu=view.findViewById(R.id.edt_nhaptenloai_udchitieu);
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
        IconAdapter adapter=new IconAdapter(getContext(),list,R.layout.item_icon);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chonanh=list.get(i);
                imv_icon_udchitieu.setImageResource(list.get(i));
            }
        });
        ApiService.apiservice.getDSLCTBYID(idlctieu).enqueue(new Callback<LoaiChiTieuEntity>() {
            @Override
            public void onResponse(Call<LoaiChiTieuEntity> call, Response<LoaiChiTieuEntity> response) {
                loaiChiTieuEntity=response.body();
                imv_icon_udchitieu.setImageResource(loaiChiTieuEntity.getIcon());
                edt_nhaptenloai_udchitieu.setText(loaiChiTieuEntity.getTenLoai());
            }

            @Override
            public void onFailure(Call<LoaiChiTieuEntity> call, Throwable t) {

            }
        });
        tv_udchitieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiChiTieuEntity.setMaLoai(idlctieu);
                loaiChiTieuEntity.setTenLoai(edt_nhaptenloai_udchitieu.getText().toString());
                    loaiChiTieuEntity.setIcon(chonanh);
                Toast.makeText(getActivity(), idlctieu+"Thành Công", Toast.LENGTH_SHORT).show();

                ApiService.apiservice.updateLCT(idlctieu,loaiChiTieuEntity).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getActivity(), "Thành Công", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_updateLoaiChiTieuFragment_to_phanLoaiFragment);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_loai_chi_tieu, container, false);
    }

}