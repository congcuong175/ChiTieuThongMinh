package com.example.chitieuthongminh.Adapter;

import static com.example.chitieuthongminh.ChiTieuFragment.idlctieu;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;
import com.example.chitieuthongminh.R;
import com.example.chitieuthongminh.addLoaiThuNhap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiChiTieuAdapter extends RecyclerView.Adapter<LoaiChiTieuAdapter.LoaiChiTieuViewHolder>{
    List<LoaiChiTieuEntity> chiTieuEntities;
    Dialog dialog;

    public void setData(List<LoaiChiTieuEntity> chiTieuEntities){
        this.chiTieuEntities=chiTieuEntities;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LoaiChiTieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaichitieu,parent,false);
        dialog=new Dialog(parent.getContext());
        dialog.setContentView(R.layout.itemloaddata);
        dialog.setCancelable(false);
        Window window=dialog.getWindow();
        if(window!=null){
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            layoutParams.gravity= Gravity.CENTER;
            window.setAttributes(layoutParams);
        }

        return new LoaiChiTieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiTieuViewHolder holder, int position) {
        LoaiChiTieuEntity lct=chiTieuEntities.get(position);

        holder.loaichitieu.setImageResource(lct.getIcon());
        holder.tv_chuDe.setText(lct.getTenLoai());
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                ApiService.apiservice.xoaLCT(lct.getMaLoai()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dialog.hide();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.hide();
                    }
                });
            }
        });
        holder.tv_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idlctieu=lct.getMaLoai();
                Navigation.findNavController(view).navigate(R.id.action_phanLoaiFragment_to_updateLoaiChiTieuFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
         if(chiTieuEntities.size()!=0){
             return chiTieuEntities.size();
        }
         else return 0;
    }

    public class LoaiChiTieuViewHolder extends RecyclerView.ViewHolder{
        ImageView btn_xoa,loaichitieu;
        TextView tv_chuDe,tv_Sua;
        public LoaiChiTieuViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_xoa=itemView.findViewById(R.id.btn_xoaLoaiChiTieu);
            loaichitieu=itemView.findViewById(R.id.imv_loaichitieu);
            tv_chuDe=itemView.findViewById(R.id.tv_loaichitieu);
            tv_Sua=itemView.findViewById(R.id.tv_suachitieu);
        }
    }

}
