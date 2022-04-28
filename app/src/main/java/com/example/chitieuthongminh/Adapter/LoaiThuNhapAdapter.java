package com.example.chitieuthongminh.Adapter;

import static com.example.chitieuthongminh.ChiTieuFragment.idlctieu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;
import com.example.chitieuthongminh.Entity.LoaiThuNhapEntity;
import com.example.chitieuthongminh.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiThuNhapAdapter extends RecyclerView.Adapter<LoaiThuNhapAdapter.LoaiThuNhapViewHolder> {
    List<LoaiThuNhapEntity> thuNhapEntities;


    public void setData(List<LoaiThuNhapEntity> thuNhapEntities) {
        this.thuNhapEntities = thuNhapEntities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiThuNhapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaithunhap, parent, false);
        return new LoaiThuNhapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuNhapViewHolder holder, int position) {
        LoaiThuNhapEntity lct = thuNhapEntities.get(position);
        holder.loaichitieu.setImageResource(lct.getIcon());
        Log.e("TAG",lct.getIcon()+"");
        holder.tv_chuDe.setText(lct.getTenLoai());
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService.apiservice.xoaLTN(lct.getMaLoai()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
        holder.tv_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idlctieu=lct.getMaLoai();
                Navigation.findNavController(view).navigate(R.id.action_phanLoaiFragment_to_updateLoaiThuNhapFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thuNhapEntities.size();
    }

    public class LoaiThuNhapViewHolder extends RecyclerView.ViewHolder {
        ImageView btn_xoa, loaichitieu;
        TextView tv_chuDe, tv_Sua;

        public LoaiThuNhapViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_xoa = itemView.findViewById(R.id.btn_xoaLoaiThuNhap);
            loaichitieu = itemView.findViewById(R.id.imv_loaiThuNhap);
            tv_chuDe = itemView.findViewById(R.id.tv_loaThuNhap);
            tv_Sua = itemView.findViewById(R.id.tv_suaThuNhap);
        }
    }
}

