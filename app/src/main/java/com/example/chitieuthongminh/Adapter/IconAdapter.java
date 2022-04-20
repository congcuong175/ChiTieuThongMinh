package com.example.chitieuthongminh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.chitieuthongminh.R;

import java.util.List;

public class IconAdapter extends BaseAdapter {
    Context context;
    List<Integer>list;
    int layout;

    public IconAdapter(Context context, List<Integer> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);
        ImageView imv_icon=view.findViewById(R.id.imv_icon);
        Integer icon=list.get(i);
        imv_icon.setImageResource(icon);
        return view;
    }
}
