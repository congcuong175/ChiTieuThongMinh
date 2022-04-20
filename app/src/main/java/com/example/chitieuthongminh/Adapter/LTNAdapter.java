package com.example.chitieuthongminh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chitieuthongminh.Entity.LoaiThuNhapEntity;
import com.example.chitieuthongminh.R;

import java.util.List;

public class LTNAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiThuNhapEntity> list;

    public LTNAdapter(Context context, int layout, List<LoaiThuNhapEntity> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        ImageView imv = view.findViewById(R.id.imv_icon_layout);
        TextView tv = view.findViewById(R.id.tv_chude_layout);

        LoaiThuNhapEntity lct = list.get(i);
        tv.setText(lct.getTenLoai());
        imv.setImageResource(lct.getIcon());
        return view;
    }
}