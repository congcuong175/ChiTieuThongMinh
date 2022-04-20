package com.example.chitieuthongminh;

import static com.example.chitieuthongminh.ChiTieuFragment.idlctieu;

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
import com.example.chitieuthongminh.Entity.LoaiThuNhapEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateLoaiThuNhapFragment extends Fragment {
    GridView gridView;
    List<Integer> list;
    ImageView imv_icon_udthunhap;
    TextView tv_udthunhap;
    EditText edt_nhaptenloai_udthunhap;
    LoaiThuNhapEntity loaiThuNhapEntity=new LoaiThuNhapEntity();
    int chonanh=0;
    public UpdateLoaiThuNhapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TrangChu tc= (TrangChu) getActivity();
        tc.getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).navigate(R.id.action_updateLoaiThuNhapFragment_to_phanLoaiFragment);
            }
        });


        gridView=view.findViewById(R.id.list_icon_ud);
        imv_icon_udthunhap=view.findViewById(R.id.imv_icon_udchitieu);
        tv_udthunhap=view.findViewById(R.id.btn_hoanthanh_ud);
        edt_nhaptenloai_udthunhap=view.findViewById(R.id.edt_nhaptenloai_udchitieu);
        list=new ArrayList<>();

        list.add(R.drawable.icon);
        list.add(R.drawable.ic_pen);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        list.add(R.drawable.icon);
        IconAdapter adapter=new IconAdapter(getContext(),list,R.layout.item_icon);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chonanh=list.get(i);
                imv_icon_udthunhap.setImageResource(list.get(i));
            }
        });
        ApiService.apiservice.getDSLTNBYID(idlctieu).enqueue(new Callback<LoaiThuNhapEntity>() {
            @Override
            public void onResponse(Call<LoaiThuNhapEntity> call, Response<LoaiThuNhapEntity> response) {
                loaiThuNhapEntity=response.body();
                imv_icon_udthunhap.setImageResource(loaiThuNhapEntity.getIcon());
                edt_nhaptenloai_udthunhap.setText(loaiThuNhapEntity.getTenLoai());
            }

            @Override
            public void onFailure(Call<LoaiThuNhapEntity> call, Throwable t) {

            }
        });
        tv_udthunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiThuNhapEntity.setMaLoai(idlctieu);
                loaiThuNhapEntity.setTenLoai(edt_nhaptenloai_udthunhap.getText().toString());
                loaiThuNhapEntity.setIcon(chonanh);
                Toast.makeText(getActivity(), idlctieu+"Thành Công", Toast.LENGTH_SHORT).show();

                ApiService.apiservice.updateLTN(idlctieu,loaiThuNhapEntity).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getActivity(), "Thành Công", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_updateLoaiThuNhapFragment_to_phanLoaiFragment);
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
        return inflater.inflate(R.layout.fragment_update_loai_thu_nhap, container, false);
    }
}