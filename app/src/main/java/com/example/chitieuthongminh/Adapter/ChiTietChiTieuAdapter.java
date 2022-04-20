package com.example.chitieuthongminh.Adapter;

import static com.example.chitieuthongminh.ChiTieuFragment.idchitietct;
import static com.example.chitieuthongminh.TrangChu.lnlout_chipnv;
import static com.example.chitieuthongminh.TrangChuFragment.currencyFormat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitieuthongminh.Entity.ChiTietChiTieu;
import com.example.chitieuthongminh.Entity.ChiTietEntity;
import com.example.chitieuthongminh.R;

import java.util.ArrayList;
import java.util.List;

public class ChiTietChiTieuAdapter extends RecyclerView.Adapter<ChiTietChiTieuAdapter.ChiTietChiTieuViewHolder>{
    private List<ChiTietChiTieu> chiTietChiTieus;
    public void SetData(List<ChiTietChiTieu>chiTietChiTieus){
        this.chiTietChiTieus=chiTietChiTieus;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ChiTietChiTieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemchitietchitieu,parent,false);
        return new ChiTietChiTieuAdapter.ChiTietChiTieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietChiTieuViewHolder holder, int position) {
        ChiTietChiTieu ct=chiTietChiTieus.get(position);
        holder.tv_chuDe.setText(ct.getTenChiTieu());
        holder.tv_chiTiet.setText(ct.getGhiChu());
        if(ct.isChiTieu()){
            holder.tv_tien.setText(" - "+currencyFormat(ct.getTien()+""));

        }
        else {
            holder.tv_tien.setText(" + "+currencyFormat(ct.getTien()+""));
        }

        holder.item_chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnlout_chipnv.setVisibility(View.GONE);
                idchitietct=ct.getMaChiTieu();
                Navigation.findNavController(view).navigate(R.id.action_trangChuFragment_to_chitietFragment);
            }
        });
        holder.imv_icon_ctct.setImageResource(ct.getIdicon());
    }

    @Override
    public int getItemCount() {
        return chiTietChiTieus.size();
    }

    public class ChiTietChiTieuViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_chuDe, tv_chiTiet, tv_tien;
        LinearLayout item_chitiet;
        ImageView imv_icon_ctct;
        public ChiTietChiTieuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_chuDe = itemView.findViewById(R.id.tv_chuDe);
            tv_chiTiet = itemView.findViewById(R.id.tv_chiTiet);
            tv_tien = itemView.findViewById(R.id.tv_tien);
            item_chitiet=itemView.findViewById(R.id.item_chitiet);
            imv_icon_ctct=itemView.findViewById(R.id.imv_icon_ctct);
        }
    }
}
