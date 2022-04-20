package com.example.chitieuthongminh.Adapter;

import static com.example.chitieuthongminh.TrangChu.lnlout_chipnv;
import static com.example.chitieuthongminh.TrangChuFragment.currencyFormat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitieuthongminh.Entity.ChiTietChiTieu;
import com.example.chitieuthongminh.Entity.ChiTietEntity;
import com.example.chitieuthongminh.R;

import java.util.ArrayList;
import java.util.List;

public class ChiTieuAdapter extends RecyclerView.Adapter<ChiTieuAdapter.ChiTieuViewHolder> {
    private List<ChiTietEntity> chiTietEntities;
    Context context;
    int tongtienchi=0,tongtienthu=0;
    ChiTietChiTieuAdapter adapter;
    public ChiTieuAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ChiTietEntity> chiTietEntities) {
        this.chiTietEntities = chiTietEntities;
       // Log.e("ERROR",chiTietEntities.size()+"");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChiTieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemchitieu, parent, false);
        return new ChiTieuAdapter.ChiTieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTieuViewHolder holder, int position) {
        ChiTietEntity ct = chiTietEntities.get(position);
        holder.tv_ngay.setText(ct.getNgay());
        tongtienchi=0;
        tongtienthu=0;
        for (ChiTietChiTieu ct2 : ct.getList()){
            if(ct2.isChiTieu()){
                tongtienchi+= ct2.getTien();
            }
            else {
                tongtienthu+=ct2.getTien();
            }
        }

       if(tongtienchi>0 && tongtienthu>0){
           holder.tv_tongtien.setText("Chi Tiêu: "+currencyFormat(tongtienchi+"") +" "+"Thu Nhập: "+currencyFormat(tongtienthu+""));
       }
       else {
           if(tongtienchi>0){
               holder.tv_tongtien.setText("Chi Tiêu: "+currencyFormat(tongtienchi+""));
           }
           else if(tongtienthu>0){
               holder.tv_tongtien.setText("Thu Nhập: "+currencyFormat(tongtienthu+""));
           }
           else {
               holder.tv_tongtien.setText("Chi Tiêu: "+currencyFormat(tongtienchi+""));
           }
       }

        adapter=new ChiTietChiTieuAdapter();
        adapter.SetData(ct.getList());

        LinearLayoutManager layoutManager=new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return chiTietEntities.size();
    }

    public class ChiTieuViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ngay,tv_tongtien;
        RecyclerView recyclerView;
        public ChiTieuViewHolder(@NonNull View itemView) {
            super(itemView);
        tv_ngay=itemView.findViewById(R.id.tv_ngay);
        tv_tongtien=itemView.findViewById(R.id.tv_tongtien);
        recyclerView=itemView.findViewById(R.id.rcv_chitietchitieu);
        }
    }
}
