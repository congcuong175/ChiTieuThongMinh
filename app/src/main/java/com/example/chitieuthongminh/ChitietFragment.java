package com.example.chitieuthongminh;

import static com.example.chitieuthongminh.ChiTieuFragment.idchitietct;
import static com.example.chitieuthongminh.TrangChu.chipNavigationBar;
import static com.example.chitieuthongminh.TrangChu.lnlout_chipnv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.ChiTietChiTieu;
import com.example.chitieuthongminh.Entity.ChiTietEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChitietFragment extends Fragment {

    Button button;
    TextView tv_chitiet_tien,tv_tenloai_chitieu,tv_chitiet_ngay,tv_chitiet_ghichu,tv_tenchude_chitiet,tv_xoa;
    ImageView imv_icon_chitiet;
    public static ChiTietChiTieu chitiet_chitieu;
    public ChitietFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button=view.findViewById(R.id.btn_chinhsua);
        tv_chitiet_tien=view.findViewById(R.id.tv_chitiet_tien);
        tv_tenloai_chitieu=view.findViewById(R.id.tv_tenloai_chitieu);
        tv_chitiet_ngay=view.findViewById(R.id.tv_chitiet_ngay);
        tv_chitiet_ghichu=view.findViewById(R.id.tv_chitiet_ghichu);
        tv_tenchude_chitiet=view.findViewById(R.id.tv_tenchude_chitiet);
        imv_icon_chitiet=view.findViewById(R.id.imv_icon_chitiet);
        tv_xoa=view.findViewById(R.id.tv_xoa);
        ApiService.apiservice.getCTBYID(idchitietct).enqueue(new Callback<ChiTietChiTieu>() {
            @Override
            public void onResponse(Call<ChiTietChiTieu> call, Response<ChiTietChiTieu> response) {
                    if(response.body()!=null){
                        chitiet_chitieu=response.body();
                        tv_chitiet_ghichu.setText(response.body().getGhiChu());
                        tv_chitiet_tien.setText(response.body().getTien()+"");
                        tv_chitiet_ngay.setText(response.body().getNgayThang());
                        if(response.body().isChiTieu()){
                            tv_tenloai_chitieu.setText("Chi tiêu");
                        }
                        else {
                            tv_tenloai_chitieu.setText("Thu nhập");
                        }
                        imv_icon_chitiet.setImageResource(response.body().getIdicon());
                        tv_tenchude_chitiet.setText(response.body().getTenChiTieu());

                    }
            }

            @Override
            public void onFailure(Call<ChiTietChiTieu> call, Throwable t) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_chitietFragment_to_updateChiTieuFragment);
            }
        });
        tv_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService.apiservice.xoaCT(idchitietct).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                        lnlout_chipnv.setVisibility(View.VISIBLE);
                        Navigation.findNavController(view).navigate(R.id.action_chitietFragment_to_trangChuFragment);
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
        return inflater.inflate(R.layout.fragment_chitiet, container, false);
    }
}